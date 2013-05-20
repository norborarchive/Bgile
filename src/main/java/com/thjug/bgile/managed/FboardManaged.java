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
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@SessionScoped
public class FboardManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(FboardManaged.class);
	private Board board;
	@Inject
	private transient BoardFacade facade;

	@PostConstruct
	public void initial() {
		board = new Board();
	}

	public String linkToForm() {
		board = new Board();
		return redirect("fboard");
	}

	public String linkToForm(final String projectid) {
		try {
			board = facade.findById(Integer.valueOf(projectid));
		} catch (final Exception e) {
			addErrorMessage(e.getMessage(), projectid);
		}
		return redirect("fboard");
	}

	public String linkToGrant(final String projectid) {
		return redirect("grants");
	}

	public String save() {
		try {
			board.setUpdateby(getAccountId());
			if (board.getId() == null) {
				facade.create(board);
			} else {
				facade.edit(board);
			}
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return redirect("dashboard");
	}

	public String remove() {
		try {
			board.setUpdateby(getAccountId());
			facade.remove(board);
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
