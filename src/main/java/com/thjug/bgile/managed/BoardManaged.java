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
import com.thjug.bgile.entity.Card;
import com.thjug.bgile.entity.State;
import com.thjug.bgile.facade.CardFacade;
import com.thjug.bgile.facade.BoardFacade;
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

	private Board board;
	private Card card;
	private List<Card> cardList;
	private transient Dashboard dashboard;
	@Inject
	private transient BoardFacade boardFacade;
	@Inject
	private transient CardFacade cardFacade;

	@PostConstruct
	public void initial() {
		final Integer boardid = getBoardIdfromAttribute();
		if (boardid != null) {
			getSession().setAttribute("boardid", boardid);
			board = getBoard(boardid);
			loadCards(boardid);
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
		loadCards(board.getId());
		renderDashboard();
		return null;
	}

	public void handleReorder(final DashboardReorderEvent event) {
		LOG.debug("Reordered: {}, Item index: {}, Column index: {}, Sender index: {}", event.getWidgetId(), event
				.getItemIndex(), event.getColumnIndex(), event.getSenderColumnIndex());

		final Integer storyid = Integer.valueOf(event.getWidgetId().replace("ID", Constants.EMPTY));
		final char fromsate = event.getSenderColumnIndex().toString().charAt(0);
		final char tostate = event.getColumnIndex().toString().charAt(0);
		LOG.info("Moved ID: {} from state {} to state {}", storyid, fromsate, tostate);

		try {
			cardFacade.move(getAccountId(), storyid, fromsate, tostate);
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			addErrorMessage("Cannot move ID" + storyid, null);
		}

		loadCards(board.getId());
		renderDashboard();
	}

	private void loadCards(final Integer projectid) {
		try {
			cardList = cardFacade.findAllByBoardId(getAccountId(), projectid);
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private void renderDashboard() {
		final FacesContext fc = getFacesInstance();
		final Application application = fc.getApplication();

		dashboard = (Dashboard) application.createComponent(fc, DASHBOARD, DASHBOARD_RENDERER);
		dashboard.setId("dashboard");

		final DashboardModel model = new DefaultDashboardModel();
		for (int i = 0, n = State.values().length; i < n; i++) {
			final DashboardColumn column = new DefaultDashboardColumn();
			model.addColumn(column);
		}
		dashboard.setModel(model);

		for (final Card us : cardList) {
			final Panel panel = (Panel) application.createComponent(fc, PANEL, PANEL_RENDERER);
			panel.setId("ID" + us.getId().toString());
			panel.setHeader("<i class=\"icon-edit\" style=\"padding-right: 4px;\"></i><a href='/bgile/fcard/"
					+ us.getId() + "'>" + "ID" + us.getId() + "</a>");
			panel.setClosable(false);
			panel.setToggleable(false);

			if (us.getOwner() != null) {
				panel.setFooter(us.getOwner().getFirstname() + " " + us.getOwner().getLastname());
			}

			final HtmlOutputText text = new HtmlOutputText();
			text.setEscape(false);
			text.setValue("<pre>" + us.getStory() + "</pre>");
			panel.getChildren().add(text);

			dashboard.getChildren().add(panel);

			final DashboardColumn column = model.getColumn(Integer.valueOf(Character.toString(us.getStateid())));
			column.addWidget(panel.getId());
		}
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(final Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(final Card card) {
		this.card = card;
	}

}
