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
public class DashboardManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DashboardManaged.class);
	private List<Board> boards;
	@Inject
	private transient BoardFacade facade;

	@PostConstruct
	public void initial() {
		try {
			boards = facade.findAllByAccount(getLoginId());
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public String linkToList() {
		return "dashboard";
	}

	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(final List<Board> boards) {
		this.boards = boards;
	}

}
