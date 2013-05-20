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

import com.thjug.bgile.facade.impl.BoardFacadeImpl;
import com.google.inject.ImplementedBy;
import com.thjug.bgile.entity.Board;
import java.util.List;

/**
 *
 * @author @nuboat
 */
@ImplementedBy(BoardFacadeImpl.class)
public interface BoardFacade extends AbstractFacade {

	public Board findById(final Integer id) throws Exception;

	public Board create(final Board board) throws Exception;

	public Board edit(final Board board) throws Exception;

	public void remove(final Board board) throws Exception;

	public List<Board> findAllByAccount(final Integer accountid) throws Exception;

}
