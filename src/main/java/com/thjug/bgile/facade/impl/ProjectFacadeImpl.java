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

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.Project;
import com.thjug.bgile.entity.Projectaccount;
import com.thjug.bgile.facade.ProjectFacade;
import com.thjug.bgile.interceptor.Logging;
import com.thjug.bgile.service.AccountService;
import com.thjug.bgile.service.ProjectService;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author @nuboat
 */
public class ProjectFacadeImpl implements ProjectFacade {

	@Inject
	private ProjectService service;

	@Inject
	private AccountService accountService;

	@Logging
	@Override
	@Transactional
	public Project findById(Integer id) throws Exception {
		return service.find(id);
	}

	@Logging
	@Override
	@Transactional
	public Project create(Project project) throws Exception {
		return service.create(project);
	}

	@Logging
	@Override
	@Transactional
	public Project edit(Project project) throws Exception {
		return service.edit(project);
	}

	@Logging
	@Override
	@Transactional
	public void remove(Project project) throws Exception {
		service.remove(project);
	}

	@Logging
	@Override
	@Transactional
	public List<Project> findAllByAccount(final Integer accountid) throws Exception {
		final Account account = accountService.find(accountid);

		final List<Project> projects = new LinkedList<>();
		for (final Projectaccount p : account.getProjectaccountList()) {
			projects.add(p.getProjectid());
		}

		return projects;
	}}
