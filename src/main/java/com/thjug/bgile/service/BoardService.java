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
package com.thjug.bgile.service;

import com.thjug.bgile.define.Enable;
import com.thjug.bgile.define.Private;
import com.thjug.bgile.define.Status;
import com.thjug.bgile.entity.Board;

/**
 * 
 * @author @nuboat
 */
public class BoardService extends AbstractService<Board> {
	
	private static final int DEFAULT_MAXCARD = 64;
		
	public BoardService() {
		super(Board.class);
	}
	
	public void newBoard(final Board board) {
		board.setMaxcard(DEFAULT_MAXCARD);
		board.setEnableid(Enable.T);
		board.setStatusid(Status.L);
		board.setPrivateid(Private.T);
		create(board);
	}

}
