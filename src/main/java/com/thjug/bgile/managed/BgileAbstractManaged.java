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
package com.thjug.bgile.managed;

import com.google.inject.Inject;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.facade.BoardFacade;
import java.util.List;

/**
 *
 * @author @nuboat
 */
public abstract class BgileAbstractManaged extends AccountAbstractManaged {

	@Inject
	private transient BoardFacade boardFacade;

	protected BoardFacade getBoardFacade() {
		return boardFacade;
	}

	protected Board getBoard(final Integer boardid) {
		return boardFacade.findById(boardid);
	}

	protected Integer getBoardIdfromAttribute() {
		final List<String> attributes = getAttribute("ATTRIBUTES");
		if (attributes != null && attributes.size() > 0) {
			final Integer boardid = Integer.valueOf(attributes.get(1));
			getSession().setAttribute("boardid", boardid);
			return boardid;
		} else {
			return (Integer) getSession().getAttribute("boardid");
		}
	}

}
