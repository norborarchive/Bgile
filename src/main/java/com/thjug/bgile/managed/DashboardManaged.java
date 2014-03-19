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

import com.thjug.bgile.entity.BoardAccount;
import com.thjug.bgile.facade.BoardAccountFacade;
import java.util.List;
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
@ManagedBean
@ViewScoped
public class DashboardManaged extends AccountAbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DashboardManaged.class);

	private List<BoardAccount> boards;

	@Inject
	private transient BoardAccountFacade facade;

	@PostConstruct
	public void initial() {
		try {
			boards = facade.findAllByAccount(getPrincipal());
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			addErrorMessage("Cannot load Board data of Account {} ", getPrincipal().getUsername());
		}

	}

	public String linkToList() {
		return "dashboard";
	}

	public List<BoardAccount> getBoards() {
		return boards;
	}

	public void setBoards(final List<BoardAccount> boards) {
		this.boards = boards;
	}

}
