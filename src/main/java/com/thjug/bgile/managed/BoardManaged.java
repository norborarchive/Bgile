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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.Userstory;
import com.thjug.bgile.facade.BoardFacade;
import com.thjug.bgile.facade.UserstoryFacade;
import com.thjug.bgile.util.Constants;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@ViewScoped
public class BoardManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(BoardManaged.class);

	private static final String DASHBOARD = "org.primefaces.component.Dashboard";
	private static final String DASHBOARD_RENDERER = "org.primefaces.component.DashboardRenderer";

	private static final String PANEL = "org.primefaces.component.Panel";
	private static final String PANEL_RENDERER = "org.primefaces.component.PanelRenderer";

	public static final int DEFAULT_COLUMN_COUNT = 4; // 0 = Plan, 1 = Process, 2 = Test, 3 = Done, 4 = Archive
	private Board board;
	private Userstory userstory;
	private List<Userstory> userstoryList;
	private Dashboard dashboard;
	@Inject
	private transient BoardFacade boardFacade;
	@Inject
	private transient UserstoryFacade userstoryFacade;

	@PostConstruct
	public void initial() {
		final Integer boardid = getBoardIdfromAttribute();
		if (boardid != null) {
			getSession().setAttribute("boardid", boardid);
			board = getBoard(boardid);
			loadUserstory(boardid);
			renderDashboard();
		} else {
			addInfoMessage("Board ID " + boardid + " not found.", null);
		}
	}

	private Board getBoard(final Integer boardid) {
		try {
			return boardFacade.findById(getAccountId(), boardid);
		} catch (final Exception e) {
			addErrorMessage("Board " + boardid + " not found.", null);
			return null;
		}
	}

	private Integer getBoardIdfromAttribute() {
		final List<String> attributes = (List<String>) getAttribute("ATTRIBUTES");
		if (attributes != null && attributes.size() > 0) {
			return Integer.valueOf(attributes.get(0));
		} else {
			return (Integer) getSession().getAttribute("boardid");
		}
	}

	public String linkToBoard() {
		return redirect("board");
	}

	public String refresh() {
		LOG.info("Refresh Board: {}", board.getId());
		loadUserstory(board.getId());
		renderDashboard();
		return null;
	}

	public void handleReorder(final DashboardReorderEvent event) {
		LOG.debug("Reordered: {}, Item index: {}, Column index: {}, Sender index: {}",
				event.getWidgetId(), event.getItemIndex(), event.getColumnIndex(), event.getSenderColumnIndex());

		final Integer storyid = Integer.valueOf(event.getWidgetId().replace("US", Constants.EMPTY));
		final char fromsate = event.getSenderColumnIndex().toString().charAt(0);
		final char tostate = event.getColumnIndex().toString().charAt(0);
		LOG.info("Moved US: {} from state {} to state {}", storyid, fromsate, tostate);

		try {
			userstoryFacade.move(getAccountId(), storyid, fromsate, tostate);
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			addErrorMessage("Cannot move US" + storyid, null);
		}

		loadUserstory(board.getId());
		renderDashboard();
	}

	private void loadUserstory(final Integer projectid) {
		try {
			userstoryList = userstoryFacade.findAllByBoardId(getAccountId(), projectid);
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private void renderDashboard() {
		final FacesContext fc = getFacesInstance();
		final Application application = fc.getApplication();

		dashboard = (Dashboard) application.createComponent(fc, DASHBOARD, DASHBOARD_RENDERER);
		dashboard.setId("dashboard");

		DashboardColumn column;
		final DashboardModel model = new DefaultDashboardModel();
		for (int i = 0, n = DEFAULT_COLUMN_COUNT; i < n; i++) {
			column = new DefaultDashboardColumn();
			model.addColumn(column);
		}
		dashboard.setModel(model);

		Panel panel;
		HtmlOutputText text;
		for (final Userstory us : userstoryList) {
			panel = (Panel) application.createComponent(fc, PANEL, PANEL_RENDERER);
			panel.setId("US" + us.getId().toString());
			panel.setHeader("<i class=\"icon-edit\" style=\"padding-right: 4px;\"></i><a href='/bgile/fstory/" + us.getId() + "'>" + "US" + us.getId() + "</a>");
			panel.setClosable(false);
			panel.setToggleable(false);

			if (us.getOwner() != null) {
				panel.setFooter(us.getOwner().getFirstname() + " " + us.getOwner().getLastname());
			}

			text = new HtmlOutputText();
			text.setEscape(false);
			text.setValue("<pre>" + us.getStory() + "</pre>");
			panel.getChildren().add(text);

			dashboard.getChildren().add(panel);

			column = model.getColumn(Integer.valueOf(Character.toString(us.getStateid())));
			column.addWidget(panel.getId());
		}
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(final Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public Userstory getUserstory() {
		return userstory;
	}

	public void setUserstory(Userstory userstory) {
		this.userstory = userstory;
	}

}
