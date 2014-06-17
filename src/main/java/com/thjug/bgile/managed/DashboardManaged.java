/*
 * <pre>
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
 * </pre>
 */
package com.thjug.bgile.managed;

import com.thjug.bgile.entity.BoardAccount;
import com.thjug.bgile.entity.Burndown;
import com.thjug.bgile.facade.BoardAccountFacade;
import com.thjug.bgile.facade.BurndownFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@SessionScoped
@ManagedBean(name = "dashboard")
public class DashboardManaged extends AccountAbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DashboardManaged.class);

	private List<BoardAccount> boardaccounts;

	@Inject
	private transient BoardAccountFacade facade;

	@Inject
	private transient BurndownFacade burndownFacade;

	@PostConstruct
	public void initial() {
		try {
			boardaccounts = facade.findAllByAccount(getPrincipal());
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			addErrorMessage("Cannot load Board data of Account {} ", getPrincipal().getUsername());
		}
	}

	public CartesianChartModel burndown(final BoardAccount ba) {
		final ChartSeries series = new ChartSeries("burndown chart");
		final List<Burndown> burndowns = burndownFacade.findByBoardAccunt(ba);

		int x = 0;
		for (final Burndown b : burndowns) {
			series.set(x++, b.getEstimate() - b.getDone());
		}

		final CartesianChartModel model = new CartesianChartModel();
		model.addSeries(series);

		return model;
	}

	public String linkToList() {
		return "dashboard";
	}

	public List<BoardAccount> getBoardaccounts() {
		return boardaccounts;
	}

	public void setBoardaccounts(final List<BoardAccount> boardaccounts) {
		this.boardaccounts = boardaccounts;
	}

}
