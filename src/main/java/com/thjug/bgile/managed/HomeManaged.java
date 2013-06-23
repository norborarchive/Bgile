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

import com.thjug.bgile.util.Constants;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@RequestScoped
public final class HomeManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final String CURRENT_PAGE = "current-page";

	public boolean isHasSession() {
		return (getAccountId() != null) ? true : false;
	}

	public String getCurrentHome() {
		return (getViewId().contains("home")) ? CURRENT_PAGE : Constants.EMPTY;
	}

	public String getCurrentProjects() {
		return (getViewId().contains("dashboard")) ? CURRENT_PAGE : Constants.EMPTY;
	}

	public String getCurrentSignin() {
		return (getViewId().contains("signin")) ? CURRENT_PAGE : Constants.EMPTY;
	}

	public String getCurrentContact() {
		return (getViewId().contains("contact")) ? CURRENT_PAGE : Constants.EMPTY;
	}

}
