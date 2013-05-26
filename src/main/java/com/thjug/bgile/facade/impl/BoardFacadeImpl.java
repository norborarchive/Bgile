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
	public Board create(final Integer accountid, final Board board) throws Exception {
		board.setUpdateby(accountid);
		board.setEnable(TRUE);
		board.setStatusid(LIVE);
		service.create(board);

		final Boardaccount ba = new Boardaccount();
		ba.setBoard(board);
		ba.setAccount(accountService.find(accountid));
		ba.setPermissionid('O');
		ba.setUpdateby(accountid);
		baService.create(ba);
		accountService.clearCache();
		return board;
	}

	@Logging
	@Override
	@Transactional
	public Board edit(final Integer accountid, final Board board) throws Exception {
		board.setUpdateby(accountid);
		final Board editedProject = service.edit(board);
		accountService.clearCache();
		return editedProject;
	}

	@Logging
	@Override
	@Transactional
	public Board remove(final Integer accountid, final Board board) throws Exception {
		try {
			board.setUpdateby(accountid);
			board.setStatusid(DEAD);
			return service.edit(board);
		} finally {
			accountService.clearCache();
		}
	}

	@Logging
	@Override
	@Transactional
	public Board findById(final Integer accountid, final Integer id) throws Exception {
		// FIXME : Shared sink Issue
		return service.find(id);
	}

	@Logging
	@Override
	@Transactional
	public List<Board> findAllByAccount(final Integer accountid) throws Exception {
		final Account account = accountService.find(accountid);

		final List<Board> boards = new LinkedList<>();
		for (final Boardaccount b : account.getBoardaccountList()) {
			if (b.getBoard().getStatusid() == LIVE && b.getBoard().getEnable() == 'T') {
				boards.add(b.getBoard());
			}
		}

		return boards;
	}
}
