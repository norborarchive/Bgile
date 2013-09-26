/*
 * Attribution
 * CC BY
 * This license lets others distribute, remix, tweak,
 * and build upon your work, even commercially,
 * as long as they credit you for the original creation.
 * This is the most accommodating of licenses offered.
 * Recommended for maximum dissemination and use of licensed materials.
 *
 * http://creativecommons.org/licenses/by/3.0/
 * http://creativecommons.org/licenses/by/3.0/legalcode
 */
package com.thjug.bgile.facade;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.Card;
import com.thjug.bgile.define.State;
import com.thjug.bgile.define.Status;
import com.thjug.bgile.entity.Cardorder;
import com.thjug.bgile.interceptor.Logging;
import com.thjug.bgile.service.BoardAccountService;
import com.thjug.bgile.service.CardService;
import com.thjug.bgile.service.CardorderService;
import com.thjug.bgile.util.Constants;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.DashboardColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
public class CardFacade {

	private static final Logger LOG = LoggerFactory.getLogger(CardFacade.class);

	@Inject
	private CardService service;

	@Inject
	private BoardAccountService boardService;

	@Inject
	private CardorderService cardorderService;

	@Logging
	@Transactional
	public Card create(final Integer accountid, final Integer boardid, final Card story) {

		final Board board = boardService.findBoardAccount(boardid, accountid).getBoard();

		story.setBoard(board);
		story.setUpdateby(accountid);
		return service.createCard(story);
	}

	@Logging
	@Transactional
	public Card edit(final Integer accountid, final Card story) {

		story.setUpdateby(accountid);
		return service.update(story);
	}

	@Logging
	@Transactional
	public Card remove(final Integer accountid, final Card story) {

		story.setStatusid(Status.D);
		story.setUpdateby(accountid);
		return service.update(story);
	}

	@Logging
	@Transactional
	public Card findById(final Integer accountid, final Integer storyid) {

		// FIXME: Shared sink
		return service.find(storyid);
	}

	@Logging
	@Transactional
	public Map<Integer, Card> findAllByBoardId(final Integer accountid, final Integer boardid) {

		try {
			final Board board = boardService.findBoardAccount(boardid, accountid).getBoard();
			List<Card> cardList = service.findCardList(board);

			final Map<Integer, Card> cardMap = new HashMap<>();
			for (final Card card : cardList) {
				cardMap.put(card.getId(), card);
			}

			return cardMap;
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	@Logging
	@Transactional
	public List<Cardorder> findCardorder(final Board board) {
		return cardorderService.find(board);
	}

	@Logging
	@Transactional
	public Card move(final Integer accountid, final Board board, final Integer storyid, final Integer fromstate,
			final Integer tostate, final List<Cardorder> orders, final List<DashboardColumn> columns) {

		final Card card = service.find(storyid);
		card.setUpdateby(accountid);
		card.setStateid(tostate);
		card.setOwner((tostate == State.Plan.getId()) ? null : new Account(accountid));
		service.update(card);

		if (fromstate.intValue() == tostate.intValue()) {
			final String orderString = genOrderString(fromstate, columns);
			updateOrInsert(accountid, board, orderString, fromstate, orders);

		} else {
			final String orderFromString = genOrderString(fromstate, columns);
			updateOrInsert(accountid, board, orderFromString, fromstate, orders);

			final String orderToString = genOrderString(tostate, columns);
			updateOrInsert(accountid, board, orderToString, tostate, orders);
		}

		return card;
	}

	private void updateOrInsert(final Integer accountid, final Board board, final String orderString,
			final Integer state, final List<Cardorder> orders) {
		boolean update = false;
		for (final Cardorder c : orders) {
			if (c.getBoard().equals(board) && c.getStateid().equals(state)) {
				c.setOrderby(orderString);
				c.setUpdateby(accountid);
				cardorderService.update(c);
				update = true;
			}
		}
		if (!update) {
			final Cardorder c = new Cardorder();
			c.setBoard(board);
			c.setStateid(state);
			c.setOrderby(orderString);
			c.setUpdateby(accountid);
			cardorderService.create(c);
		}
	}

	private String genOrderString(final Integer state, final List<DashboardColumn> columns) {
		final DashboardColumn columnstart = columns.get(state);
		final StringBuilder result = new StringBuilder();
		for (final String s : columnstart.getWidgets()) {
			result.append(s.replace("ID", Constants.EMPTY)).append(Constants.COMMA);
		}

		return result.toString();
	}
}
