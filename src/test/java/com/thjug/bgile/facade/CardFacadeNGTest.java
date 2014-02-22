/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.bgile.facade;

import com.thjug.bgile.entity.Card;
import java.util.Map;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * 
 * @author @nuboat
 */
public class CardFacadeNGTest extends AbstractFacadeNGTest {

	@Test
	public void testCreate() throws Exception {
		Integer accountid = 2;
		Integer boardid = 1;

		final CardFacade instance = injector.getInstance(CardFacade.class);

		Card story = new Card();
		story.setStory("NG Test");
		Card result = instance.create(accountid, boardid, story);
		assertNotNull(result.getId());
	}

	// @Test
	public void testEdit() throws Exception {
		Integer accountid = null;
		Card story = null;
		CardFacade instance = new CardFacade();
		Card expResult = null;
		Card result = instance.edit(accountid, story);
		assertEquals(result, expResult);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	// @Test
	public void testRemove() throws Exception {
		Integer accountid = null;
		Card story = null;
		CardFacade instance = new CardFacade();
		Card expResult = null;
		Card result = instance.remove(accountid, story);
		assertEquals(result, expResult);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	// @Test
	public void testFindById() throws Exception {
		Integer accountid = null;
		Integer storyid = null;
		CardFacade instance = new CardFacade();
		Card expResult = null;
		Card result = instance.findById(accountid, storyid);
		assertEquals(result, expResult);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	// @Test
	public void testFindAllByBoardId() throws Exception {
		Integer accountid = null;
		Integer boardid = null;
		CardFacade instance = new CardFacade();
		Map expResult = null;
		Map result = instance.findAllByBoardId(boardid);
		assertEquals(result, expResult);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}
}