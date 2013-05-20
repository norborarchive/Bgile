/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.bgile.facade.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.Userstory;
import com.thjug.bgile.facade.UserstoryFacade;
import com.thjug.bgile.interceptor.Logging;
import com.thjug.bgile.service.BoardService;
import com.thjug.bgile.service.UserstoryService;
import java.util.List;

/**
 *
 * @author @nuboat
 */
public class UserstoryFacadeImpl implements UserstoryFacade {

	@Inject
	private UserstoryService service;
	@Inject
	private BoardService boardService;

	@Logging
	@Transactional
	@Override
	public Userstory create(final Userstory story) throws Exception {
		story.setBoardid(boardService.find(story.getUpdateby())); // FIXME
		story.setStateid(STATE0);
		story.setSortorder(0);
		return service.create(story);
	}

	@Logging
	@Transactional
	@Override
	public Userstory edit(final Userstory story) throws Exception {
		return service.edit(story);
	}

	@Logging
	@Transactional
	@Override
	public void remove(final Userstory story) throws Exception {
		service.remove(story);
	}

	@Logging
	@Transactional
	@Override
	public List<Userstory> findAllByProjectid(final Integer boardid) throws Exception {
		final Board board = boardService.find(boardid);
		return service.findAll(Userstory.findByBoardId, board);
	}

}
