/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.bgile.facade.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.Userstory;
import static com.thjug.bgile.facade.AbstractFacade.FALSE;
import com.thjug.bgile.facade.UserstoryFacade;
import static com.thjug.bgile.facade.UserstoryFacade.STATE0;
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
	public Userstory create(final Integer accountid, final Integer boardid, final Userstory story) throws Exception {
		final Board board = boardService.find(boardid);
		final List<Userstory> storys = service.findLowerestinState0ByBoard(board);
		for (final Userstory us : storys) {
			us.setLowerest(FALSE);
			service.edit(us);
		}

		if (!storys.isEmpty()) {
			story.setUnderid(storys.get(storys.size() - 1).getId());
		}
		story.setBoardid(board);
		story.setLowerest(TRUE);
		story.setStateid(STATE0);
		story.setStatusid(LIVE);
		story.setUpdateby(accountid);
		return service.create(story);
	}

	@Logging
	@Transactional
	@Override
	public Userstory edit(final Integer accountid, final Userstory story) throws Exception {
		story.setUpdateby(accountid);
		return service.edit(story);
	}

	@Logging
	@Transactional
	@Override
	public Userstory remove(final Integer accountid, final Userstory story) throws Exception {
		story.setUpdateby(accountid);
		story.setStatusid(DEAD);
		return service.edit(story);
	}

	@Logging
	@Transactional
	@Override
	public Userstory findById(final Integer accountid, final Integer storyid) throws Exception {
		// FIXME: Shared sink
		return service.find(storyid);
	}

	@Logging
	@Transactional
	@Override
	public List<Userstory> findAllByBoardId(final Integer accountid, final Integer boardid) throws Exception {
		// FIXME: Shared sink
		final Board board = boardService.find(boardid);
		return service.findAll(Userstory.findByBoardId, board);
	}

}
