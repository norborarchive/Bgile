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

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author @nuboat
 */
public abstract class AbstractManaged implements Serializable {

	private static final long serialVersionUID = 1L;

	protected final String getRequestURI() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
	}

	protected final HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	protected final ServletContext getApplication() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

	protected final FacesContext getFacesInstance() {
		return FacesContext.getCurrentInstance();
	}

	protected final ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	protected final Integer getAccountId() {
		return null;
	}

	protected final void addWarnMessage(final String topic, final String message) {
		getFacesInstance().addMessage("Test ", new FacesMessage(FacesMessage.SEVERITY_WARN, topic + message, ""));
	}

	protected final void addInfoMessage(final String topic, final String message) {
		getFacesInstance().addMessage("Test ", new FacesMessage(FacesMessage.SEVERITY_INFO, topic + message, ""));
	}

	protected final void addErrorMessage(final String topic, final String message) {
		getFacesInstance().addMessage("Test ", new FacesMessage(FacesMessage.SEVERITY_ERROR, topic + message, ""));
	}

}
