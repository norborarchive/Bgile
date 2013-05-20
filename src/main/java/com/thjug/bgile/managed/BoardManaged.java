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
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.Userstory;
import com.thjug.bgile.facade.UserstoryFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@SessionScoped
public class BoardManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(BoardManaged.class);

	private static final String DASHBOARD = "org.primefaces.component.Dashboard";
	private static final String DASHBOARD_RENDERER = "org.primefaces.component.DashboardRenderer";

	private static final String PANEL = "org.primefaces.component.Panel";
	private static final String PANEL_RENDERER = "org.primefaces.component.PanelRenderer";

	public static final int DEFAULT_COLUMN_COUNT = 4; // 0 = Plan, 1 = Process, 2 = Test, 3 = Done, 4 = Archive
	private Integer projectid;
	private transient List<Userstory> userstoryList;
	private transient Dashboard dashboard;

	@Inject
	private transient UserstoryFacade userstoryFacade;

	public String linkToBoard(final String projectid) {
		LOG.debug("Click projectid: {}", projectid);
		this.projectid = Integer.valueOf(projectid);
		loadUserstory(this.projectid);
		renderDashboard();
		return redirect("board");
	}

	public String linkToBoard() {
		return redirect("board");
	}

	public String addStory() {
		return redirect("story");
	}

	public String refresh() {
		loadUserstory(this.projectid);
		renderDashboard();
		return null;
	}

	public void handleReorder(final DashboardReorderEvent event) {
		//FacesMessage message = new FacesMessage();
		//message.setSeverity(FacesMessage.SEVERITY_INFO);
		//message.setSummary("Reordered: " + event.getWidgetId());
		//message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex()
		//		+ ", Sender index: " + event.getSenderColumnIndex());

		//addMessage(message);
		LOG.info("Reordered: {}, Item index: {}, Column index: {}, Sender index: {}", event.getWidgetId(), event
				.getItemIndex(), event.getColumnIndex(), event.getSenderColumnIndex());
		addInfoMessage(event.getWidgetId(), event.getItemIndex().toString());
	}

	private void loadUserstory(final Integer projectid) {
		try {
			userstoryList = userstoryFacade.findAllByProjectid(projectid);
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
		HtmlOutputLink link;
		HtmlOutputText linkText;
		for (final Userstory us : userstoryList) {
			panel = (Panel) application.createComponent(fc, PANEL, PANEL_RENDERER);
			panel.setId("US" + us.getId().toString());
			panel.setHeader("US" + us.getId().toString());
			panel.setClosable(false);
			panel.setToggleable(false);

			if (us.getOwnerid() != null) {
				panel.setFooter(us.getOwnerid().getFirstname() + " " + us.getOwnerid().getLastname());
			}

			text = new HtmlOutputText();
			text.setEscape(false);
			text.setValue(us.getStory() + "<br />");
			panel.getChildren().add(text);

			link = new HtmlOutputLink();
			link.setValue("story.xhtml?id=" + us.getId());

			linkText = new HtmlOutputText();
			linkText.setValue("Edit");
			link.getChildren().add(linkText);
			panel.getChildren().add(link);

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

}

/*
		Account a = new Account();
		a.setId(1);
		a.setFirstname("Peerapat");
		a.setLastname("A");
		userstoryList = new LinkedList<>();

		Userstory us;
		us = new Userstory();
		us.setId(0);
		us.setStory("Test Story 0");
		us.setDescription("Test Description 0");
		us.setStateid('0');
		userstoryList.add(us);

		us = new Userstory();
		us.setId(1);
		us.setStory("Test Story 1");
		us.setDescription("Test Description 1");
		us.setOwnerid(a);
		us.setStateid('1');
		userstoryList.add(us);

		us = new Userstory();
		us.setId(2);
		us.setStory("Test Story 2");
		us.setDescription("Test Description 2");
		us.setOwnerid(a);
		us.setStateid('2');
		userstoryList.add(us);

		us = new Userstory();
		us.setId(3);
		us.setStory("Test Story 3");
		us.setDescription("Test Description 3");
		us.setOwnerid(a);
		us.setStateid('3');
		userstoryList.add(us);
 */
