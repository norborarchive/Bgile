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

import com.thjug.bgile.entity.Project;
import com.thjug.bgile.facade.ProjectFacade;
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
public class ProjectFacadeImplNGTest extends AbstractFacadeNGTest {

	private static final Logger LOG = LoggerFactory.getLogger(ProjectFacadeImplNGTest.class);

	@Test
	public void testFindById() throws Exception {
		LOG.info("findById");
		final ProjectFacade instance = injector.getInstance(ProjectFacadeImpl.class);

		final Integer expResult = 1;
		final Project result = instance.findById(1);

		assertEquals(result.getId(), expResult);
	}

	Project newProject;

	@Test
	public void testCreate() throws Exception {
		LOG.info("findById");
		final ProjectFacade instance = injector.getInstance(ProjectFacadeImpl.class);

		final Project project = new Project();
		project.setIsenable('T'); // T = true, F = False
		project.setStatusid('L'); // L = live, D = Delete
		project.setPgname("Test Project");
		project.setDescription("Training");
		project.setUpdateby(2);

		newProject = instance.create(project);
		assertNotNull(newProject);
	}

	@Test
	public void testEdit() throws Exception {
		LOG.info("edit");
		final ProjectFacade instance = injector.getInstance(ProjectFacadeImpl.class);

		newProject.setPgname("Edit Project");

		final Project result = instance.edit(newProject);
		assertEquals(result.getPgname(), "Edit Project");
	}

	@Test(expectedExceptions = EntityNotFoundException.class)
	public void testRemove() throws Exception {
		LOG.info("remove");
		final ProjectFacade instance = injector.getInstance(ProjectFacadeImpl.class);

		final Integer id = newProject.getId();

		instance.remove(newProject);

		instance.findById(id);
	}

	@Test
	public void testFindAllByAccount() throws Exception {
		LOG.info("remove");
		final ProjectFacade instance = injector.getInstance(ProjectFacadeImpl.class);

		final Integer accountId = 2;

		final List result = instance.findAllByAccount(accountId);
		assertNotEquals(result.size(), 0);
	}

}