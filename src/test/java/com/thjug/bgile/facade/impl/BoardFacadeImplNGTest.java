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

import com.thjug.bgile.entity.Board;
import com.thjug.bgile.facade.BoardFacade;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author nuboat
 */
public class BoardFacadeImplNGTest extends AbstractFacadeNGTest {

	private static final Logger LOG = LoggerFactory.getLogger(BoardFacadeImplNGTest.class);
	private static final Integer accountid = 2;

	@Test
	public void testFindById() throws Exception {
		LOG.info("findById");
		final BoardFacade instance = injector.getInstance(BoardFacadeImpl.class);

		final Integer expResult = 1;
		final Board result = instance.findById(accountid, 1);

		assertEquals(result.getId(), expResult);
	}

	@Test
	public void testCreate() throws Exception {
		LOG.info("findById");
		final BoardFacade instance = injector.getInstance(BoardFacadeImpl.class);

		final Board board = new Board();
		board.setBoardname("Test Project");
		board.setDescription("Training");
		assertNotNull(instance.create(accountid, board));
	}

	@Test
	public void testEdit() throws Exception {
		LOG.info("edit");
		final BoardFacade instance = injector.getInstance(BoardFacadeImpl.class);

		final Board board = new Board();
		board.setBoardname("Test Project");
		board.setDescription("Training");
		final Board newBoard = instance.create(accountid, board);

		newBoard.setBoardname("Edit Project");

		final Board result = instance.edit(accountid, newBoard);
		assertEquals(result.getBoardname(), "Edit Project");
	}

	@Test(expectedExceptions = EntityNotFoundException.class)
	public void testRemove() throws Exception {
		LOG.info("remove");
		final BoardFacade instance = injector.getInstance(BoardFacadeImpl.class);

		final Board board = new Board();
		board.setBoardname("Test Project");
		board.setDescription("Training");
		final Board newBoard = instance.create(accountid, board);
		final Integer boardid = newBoard.getId();

		instance.remove(accountid, newBoard);
		instance.findById(accountid, boardid);
	}

	@Test
	public void testFindAllByAccount() throws Exception {
		LOG.info("remove");
		final BoardFacade instance = injector.getInstance(BoardFacadeImpl.class);

		final List result = instance.findAllByAccount(accountid);
		assertNotEquals(result.size(), 0);
	}

}