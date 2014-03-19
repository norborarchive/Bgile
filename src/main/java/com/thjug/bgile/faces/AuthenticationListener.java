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
package com.thjug.bgile.faces;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Please look in SigninFilter.
 * 
 * @author @nuboat
 */
@Deprecated
public class AuthenticationListener implements PhaseListener {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationListener.class);

	@Override
	public void afterPhase(final PhaseEvent event) {

		final FacesContext facesContext = event.getFacesContext();
		final String currentPage = facesContext.getViewRoot().getViewId();

		LOG.info("Access ViewId: {}", currentPage);

		final boolean isLoginPage = currentPage.contains("signin");
		final HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

		if (session == null) {
			final NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
			nh.handleNavigation(facesContext, null, "signin");
		} else {
			final Integer currentUser = (Integer) session.getAttribute("accountid");

			if (!isLoginPage && currentUser == null) {
				final NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
				nh.handleNavigation(facesContext, null, "signin");
			}
		}
	}

	@Override
	public void beforePhase(final PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}
