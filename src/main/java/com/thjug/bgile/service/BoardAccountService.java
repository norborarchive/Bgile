/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.bgile.service;

import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.BoardAccount;

/**
 *
 * @author nuboat
 */
public class BoardAccountService extends AbstractService<BoardAccount> {

	public BoardAccountService() {
		super(BoardAccount.class);
	}

	public BoardAccount findBoardOfAccount(final Integer boardId, final Integer accountId) {
		final Board board = new Board(boardId);
		final Account account = new Account(accountId);

		return findOne(BoardAccount.FIND_BY_ACCOUNT_AND_BOARD, account, board);
	}
}
