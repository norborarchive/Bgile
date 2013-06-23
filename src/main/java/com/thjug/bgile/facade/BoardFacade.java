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
public interface BoardFacade {

	Board findById(final Integer accountid, final Integer id);

	Board create(final Integer accountid, final Board board);

	Board edit(final Integer accountid, final Board board);

	Board remove(final Integer accountid, final Board board);

	List<Board> findAllByAccount(final Integer accountid);
}
