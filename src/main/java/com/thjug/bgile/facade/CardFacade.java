/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.bgile.facade;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.Card;
import com.thjug.bgile.entity.State;
import com.thjug.bgile.entity.Status;
import com.thjug.bgile.interceptor.Logging;
import com.thjug.bgile.service.BoardAccountService;
import com.thjug.bgile.service.CardService;
import java.util.List;

/**
 *
 * @author @nuboat
 */
public class CardFacade {

	@Inject
	private CardService service;
	@Inject
	private BoardAccountService boardService;

	@Logging
	@Transactional
	public Card create(final Integer accountid, final Integer boardid, final Card story) {
		final Board board = boardService.findBoardOfAccount(boardid, accountid).getBoard();

		story.setBoard(board);
		story.setUpdateby(accountid);
		return service.createNewStory(story);
	}

	@Logging
	@Transactional
	public Card edit(final Integer accountid, final Card story) {
		story.setUpdateby(accountid);
		return service.edit(story);
	}

	@Logging
	@Transactional
	public Card remove(final Integer accountid, final Card story) {
		story.setStatusid(Status.D);
		story.setUpdateby(accountid);
		return service.edit(story);
	}

	@Logging
	@Transactional
	public Card findById(final Integer accountid, final Integer storyid) {
		// FIXME: Shared sink
		return service.find(storyid);
	}

	@Logging
	@Transactional
	public List<Card> findAllByBoardId(final Integer accountid, final Integer boardid) {
		final Board board = boardService.findBoardOfAccount(boardid, accountid).getBoard();
		return service.findByBoard(board);
	}

	@Logging
	@Transactional
	public Card move(final Integer accountid, final Integer storyid, final char fromstate, final char tostate) {
		final Card card = service.find(storyid);
		card.setUpdateby(accountid);
		card.setStateid(tostate);
		card.setOwner((tostate == State.Plan.getId()) ? null : new Account(accountid));

		// FIXME: REORDER PROCESS

		return service.edit(card);
	}
}
