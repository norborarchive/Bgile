/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.bgile.service;

import com.thjug.bgile.define.Permission;
import com.thjug.bgile.define.Status;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.BoardAccount;
import java.util.List;

/**
 *
 * @author @nuboat
 */
public class BoardAccountService extends AbstractService<BoardAccount> {

	public BoardAccountService() {
		super(BoardAccount.class);
	}

	public BoardAccount createBoardAccountOwner(final Integer updateby, final Integer accountid, final Board board) {
		final BoardAccount boardAccount = new BoardAccount();
		boardAccount.setAccount(new Account(accountid));
		boardAccount.setBoard(board);
		boardAccount.setPermissionid(Permission.O);
		boardAccount.setUpdateby(updateby);

		return create(boardAccount);
	}

	public BoardAccount addAccountToBoard(final Integer updateby, final Account account, final Board board) {

		final BoardAccount boardAccount = new BoardAccount();
		boardAccount.setAccount(account);
		boardAccount.setBoard(board);
		boardAccount.setPermissionid(Permission.R);
		boardAccount.setUpdateby(updateby);

		return create(boardAccount);
	}

	public BoardAccount updateBoardAccount(final Integer updateby, final Integer accountId, final Integer boardId,
			final Permission permissionid) {

		final BoardAccount boardAccount = findBoardAccount(boardId, accountId);
		boardAccount.setPermissionid(permissionid);
		boardAccount.setUpdateby(updateby);

		return update(boardAccount);
	}

	public BoardAccount findBoardAccount(final Integer boardId, final Integer accountId) {

		final Board board = new Board(boardId);
		final Account account = new Account(accountId);

		return findOne(BoardAccount.FIND_BY_ACCOUNT_AND_BOARD, account, board);
	}

	public List<BoardAccount> findBoardAccountByBoard(final Board board) {
		return findAll(BoardAccount.FIND_BY_BOARD, board);
	}

	public List<BoardAccount> findBoardAccountByAccount(final Account account) {
		return findAll(BoardAccount.FIND_BY_ACCOUNT, account, Status.L);
	}

}
