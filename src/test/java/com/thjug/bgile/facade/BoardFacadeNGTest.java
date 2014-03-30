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

import com.thjug.bgile.entity.Board;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

/**
 *
 * @author @nuboat
 */
public class BoardFacadeNGTest extends AbstractFacadeNGTest {

	private static final Integer accountid = 2;

	final BoardFacade instance = injector.getInstance(BoardFacade.class);

	private Board board;

	@Test
	public void testCreate() throws Exception {
		board = new Board();
		board.setBoardname("Test Project");
		board.setDescription("Training");
		assertNotNull(instance.create(accountid, board));
	}

	@Test
	public void testFindById() throws Exception {
		final Board result = instance.findById(board.getId());
		assertNotNull(result);
	}

	@Test
	public void testEdit() throws Exception {
		board.setBoardname("Test Project");
		board.setDescription("Training");
		board.setBoardname("Edit Project");

		final Board result = instance.edit(accountid, board);
		assertEquals(result.getBoardname(), "Edit Project");
	}

	@Test
	public void testRemove() throws Exception {
		instance.remove(accountid, board);
	}

}