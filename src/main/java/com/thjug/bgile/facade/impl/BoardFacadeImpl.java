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
package com.thjug.bgile.facade.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.Boardaccount;
import com.thjug.bgile.facade.BoardFacade;
import static com.thjug.bgile.facade.BoardFacade.LIVE;
import com.thjug.bgile.interceptor.Logging;
import com.thjug.bgile.service.AccountService;
import com.thjug.bgile.service.BoardAccountService;
import com.thjug.bgile.service.BoardService;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author @nuboat
 */
public class BoardFacadeImpl implements BoardFacade {

	@Inject
	private BoardService service;
	@Inject
	private BoardAccountService baService;
	@Inject
	private AccountService accountService;

	@Logging
	@Override
	@Transactional
	public Board findById(final Integer id) throws Exception {
		return service.find(id);
	}

	@Logging
	@Override
	@Transactional
	public Board create(final Board board) throws Exception {
		board.setEnable(TRUE);
		board.setStatusid(LIVE);
		service.create(board);

		final Boardaccount ba = new Boardaccount();
		ba.setBoardid(board);
		ba.setAccountid(accountService.find(board.getUpdateby()));
		ba.setPermissionid('O');
		ba.setUpdateby(board.getUpdateby());
		baService.create(ba);
		accountService.clearCache();
		return board;
	}

	@Logging
	@Override
	@Transactional
	public Board edit(final Board board) throws Exception {
		final Board editedProject = service.edit(board);
		accountService.clearCache();
		return editedProject;
	}

	@Logging
	@Override
	@Transactional
	public void remove(final Board board) throws Exception {
		board.setStatusid(BoardFacade.DEAD);
		service.edit(board);
		accountService.clearCache();
	}

	@Logging
	@Override
	@Transactional
	public List<Board> findAllByAccount(final Integer accountid) throws Exception {
		final Account account = accountService.find(accountid);

		final List<Board> boards = new LinkedList<>();
		for (final Boardaccount b : account.getBoardaccountList()) {
			if (b.getBoardid().getStatusid() == LIVE && b.getBoardid().getEnable() == 'T') {
				boards.add(b.getBoardid());
			}
		}

		return boards;
	}
}
