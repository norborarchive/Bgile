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

import com.thjug.bgile.define.Permission;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.BoardAccount;
import com.thjug.bgile.facade.BoardFacade;
import com.thjug.bgile.facade.GrantFacade;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author @nuboat
 */
@ViewScoped
@ManagedBean(name = "fboard")
public class FboardManaged extends BgileAbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(FboardManaged.class);

	private static final String DASHBOARD = "dashboard";

	private Board board;
	private BoardAccount boardaccount;

	@Inject
	private transient BoardFacade facade;

	@Inject
	private transient GrantFacade grant;

	@PostConstruct
	public void initial() {
		final Integer boardid = getBoardIdfromAttribute();
		if (boardid == null) {
			board = new Board();
			return;
		}

		boardaccount = grant.getBoardAccount(getPrincipal().getId(), boardid);
		if (boardaccount == null || boardaccount.getPermissionid() == Permission.R
				|| boardaccount.getPermissionid() == Permission.W) {
			setRedirect(DASHBOARD);
			return;
		}

		board = boardaccount.getBoard();
		if (board == null) {
			setRedirect(DASHBOARD);
		}
	}

	public String save() {
		try {
			board = (board.getId() == null) ? facade.create(getPrincipal().getId(), board) : facade.edit(getPrincipal()
					.getId(), board);
			return redirect(DASHBOARD);
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			addErrorMessage("Fail,", "Cannot save board data. Please contact your admin.");
			return null;
		}
	}

	public String remove() {
		try {
			facade.remove(getPrincipal().getId(), board);
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return redirect(DASHBOARD);
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(final Board board) {
		this.board = board;
	}

	public boolean isNewBoard() {
		return board != null ? board.getId() == null : false;
	}

}
