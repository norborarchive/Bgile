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
import com.thjug.bgile.define.Private;
import com.thjug.bgile.facade.BoardFacade;
import com.thjug.bgile.util.Constants;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@ViewScoped
public class FboardManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(FboardManaged.class);
	private Board board;
	@Inject
	private transient BoardFacade facade;

	@PostConstruct
	public void initial() {
		final String boardid = getBoardIdfromAttribute();
		if (boardid != null) {
			try {
				board = facade.findById(getLoginId(), Integer.valueOf(boardid));
			} catch (final Exception e) {
				LOG.error(e.getMessage(), e);
				addErrorMessage("Board: {} not found.", boardid);
			}
		} else {
			board = new Board();
		}
	}

	private String getBoardIdfromAttribute() {
		final List<String> attributes = getAttribute("ATTRIBUTES");
		return (attributes.size() > 0) ? attributes.get(1) : null;
	}

	public String save() {
		try {
			board.setPrivateid(Private.T);
			board = (board.getId() == null) ? facade.create(getLoginId(), board) : facade.edit(getLoginId(), board);
			return "dashboard";
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			addErrorMessage(e.getMessage(), Constants.EMPTY);
			return null;
		}
	}

	public String remove() {
		try {
			facade.remove(getLoginId(), board);
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return redirect("dashboard");
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(final Board board) {
		this.board = board;
	}

	public boolean isNewBoard() {
		return board.getId() == null;
	}

}
