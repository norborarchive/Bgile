/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.bgile.facade.impl;

import com.thjug.bgile.entity.Userstory;
import com.thjug.bgile.facade.AbstractFacade;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author nuboat
 */
public class UserstoryFacadeImplNGTest extends AbstractFacadeNGTest {

	@Test
	public void testCreate() throws Exception {
		Integer accountid = 2;
		Integer boardid = 1;

		final UserstoryFacadeImpl instance = injector.getInstance(UserstoryFacadeImpl.class);

		Userstory story = new Userstory();
		story.setStory("NG Test");
		Userstory result = instance.create(accountid, boardid, story);
		assertNotNull(result.getId());
		assertNotNull(result.getUnderid());
		assertEquals(result.getLowerest(), AbstractFacade.TRUE);
	}

	//@Test
	public void testEdit() throws Exception {
		System.out.println("edit");
		Integer accountid = null;
		Userstory story = null;
		UserstoryFacadeImpl instance = new UserstoryFacadeImpl();
		Userstory expResult = null;
		Userstory result = instance.edit(accountid, story);
		assertEquals(result, expResult);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	//@Test
	public void testRemove() throws Exception {
		System.out.println("remove");
		Integer accountid = null;
		Userstory story = null;
		UserstoryFacadeImpl instance = new UserstoryFacadeImpl();
		Userstory expResult = null;
		Userstory result = instance.remove(accountid, story);
		assertEquals(result, expResult);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	//@Test
	public void testFindById() throws Exception {
		System.out.println("findById");
		Integer accountid = null;
		Integer storyid = null;
		UserstoryFacadeImpl instance = new UserstoryFacadeImpl();
		Userstory expResult = null;
		Userstory result = instance.findById(accountid, storyid);
		assertEquals(result, expResult);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	//@Test
	public void testFindAllByBoardId() throws Exception {
		System.out.println("findAllByBoardId");
		Integer accountid = null;
		Integer boardid = null;
		UserstoryFacadeImpl instance = new UserstoryFacadeImpl();
		List expResult = null;
		List result = instance.findAllByBoardId(accountid, boardid);
		assertEquals(result, expResult);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}