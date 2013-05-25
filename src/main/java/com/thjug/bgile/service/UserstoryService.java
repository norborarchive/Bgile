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
package com.thjug.bgile.service;

import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.Userstory;
import com.thjug.bgile.facade.AbstractFacade;
import com.thjug.bgile.facade.UserstoryFacade;
import static com.thjug.bgile.facade.UserstoryFacade.STATE0;
import java.util.List;

/**
 *
 * @author @nuboat
 */
public final class UserstoryService extends AbstractService<Userstory> {

	public UserstoryService() {
		super(Userstory.class);
	}

	public List<Userstory> findByBoard(final Board board) {
		return findAll(Userstory.findByBoardAndStatus, board, LIVE);
	}

	public List<Userstory> findLowerestinState0ByBoard(final Board board) {
		return findAll(Userstory.findByLowerestAndBoardAndStateAndStatus, AbstractFacade.TRUE, board, UserstoryFacade.STATE0, LIVE);
	}

	public Integer clearLowerestOnStorys(final Board board) {
		List<Userstory> storys = findLowerestinState0ByBoard(board);
		for (final Userstory us : storys) {
			us.setLowerest(FALSE);
			edit(us);
		}
		return (!storys.isEmpty()) ? storys.get(0).getId() : null;
	}

	public Userstory createNewStory(final Userstory story) {
		story.setLowerest(TRUE);
		story.setStateid(STATE0);
		story.setStatusid(LIVE);
		return create(story);
	}

}
