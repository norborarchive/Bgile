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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@RequestScoped
public final class HomeManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;

	public boolean isHasSession() {
		return (getAccountId() != null) ? true : false;
	}

	public String getCurrentHome() {
		return (getViewId().contains("home")) ? "current-page" : "";
	}

	public String getCurrentProjects() {
		return (getViewId().contains("dashboard")) ? "current-page" : "";
	}

	public String getCurrentSignin() {
		return (getViewId().contains("signin")) ? "current-page" : "";
	}

	public String getCurrentContact() {
		return (getViewId().contains("contact")) ? "current-page" : "";
	}

}
