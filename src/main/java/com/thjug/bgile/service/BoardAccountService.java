/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.bgile.service;

import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.Boardaccount;

/**
 *
 * @author nuboat
 */
public class BoardAccountService extends AbstractService<Boardaccount> {

	public BoardAccountService() {
		super(Boardaccount.class);
	}

	public Boardaccount findBoardOfAccount(final Integer boardId, final Integer accountId) {
		final Board board = new Board(boardId);
		final Account account = new Account(accountId);

		return findOne(Boardaccount.findByAccountAndBoard, account, board);
	}
}
