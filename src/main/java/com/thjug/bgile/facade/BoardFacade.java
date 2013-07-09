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

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.BoardAccount;
import com.thjug.bgile.define.Enable;
import com.thjug.bgile.define.Permission;
import com.thjug.bgile.define.Status;
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
public class BoardFacade {

	private static final int DEFAULT_MAXCARD = 64;

	@Inject
	private BoardService service;
	@Inject
	private BoardAccountService baService;
	@Inject
	private AccountService accountService;

	@Logging
	@Transactional
	public Board create(final Integer accountid, final Board board) {
		board.setUpdateby(accountid);
		board.setMaxcard(DEFAULT_MAXCARD);
		board.setEnableid(Enable.T);
		board.setStatusid(Status.L);
		service.create(board);

		final BoardAccount ba = new BoardAccount();
		ba.setBoard(board);
		ba.setAccount(accountService.find(accountid));
		ba.setPermissionid(Permission.A);
		ba.setUpdateby(accountid);
		baService.create(ba);
		accountService.clearCache();
		return board;
	}

	@Logging
	@Transactional
	public Board edit(final Integer accountid, final Board board) {
		board.setUpdateby(accountid);
		final Board editedProject = service.edit(board);
		accountService.clearCache();
		return editedProject;
	}

	@Logging
	@Transactional
	public Board remove(final Integer accountid, final Board board) {
		try {
			board.setUpdateby(accountid);
			board.setStatusid(Status.D);
			return service.edit(board);
		} finally {
			accountService.clearCache();
		}
	}

	@Logging
	@Transactional
	public Board findById(final Integer accountid, final Integer id) {
		// FIXME : Shared sink Issue
		return service.find(id);
	}

	@Logging
	@Transactional
	public List<Board> findAllByAccount(final Integer accountid) {
		final Account account = accountService.find(accountid);

		final List<Board> boards = new LinkedList<>();
		for (final BoardAccount ba : account.getBoardaccountList()) {
			if (ba.getBoard().getStatusid() == Status.L && ba.getBoard().getEnableid() == Enable.T) {
				boards.add(ba.getBoard());
			}
		}

		return boards;
	}
}
