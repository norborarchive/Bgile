/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.bgile.facade.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.thjug.bgile.entity.Userstory;
import com.thjug.bgile.facade.UserstoryFacade;
import com.thjug.bgile.interceptor.Logging;
import com.thjug.bgile.service.UserstoryService;
import java.util.List;

/**
 *
 * @author @nuboat
 */
public class UserstoryFacadeImpl implements UserstoryFacade {

	@Inject
	private UserstoryService userstoryService;

	@Logging
	@Transactional
	@Override
	public Userstory create(final Userstory story) throws Exception {
		story.setStateid('0');
		story.setSortorder('0'); // FIXME
		return userstoryService.create(story);
	}

	@Override
	public Userstory edit(final Userstory story) throws Exception {
		return userstoryService.edit(story);
	}

	@Override
	public void remove(final Userstory story) throws Exception {
		userstoryService.remove(story);
	}

	@Override
	public List<Userstory> findAllByProjectid(final Integer projectid) throws Exception {
		return null;
	}
}
