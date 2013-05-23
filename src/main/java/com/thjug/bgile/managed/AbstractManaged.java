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
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author @nuboat
 */
public abstract class AbstractManaged implements Serializable {

	protected final String getRequestServletPath() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
	}

	protected final String getRequestURL() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		return request.getRequestURL().toString();
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

	protected final Map<String, String> getParams() {
		return getExternalContext().getRequestParameterMap();
	}

	protected final String getParam(final String key) {
		return getParams().get(key);
	}

	protected final String redirect(final String page) {
		return page + "?faces-redirect=true";
	}

	protected final Integer getAccountId() {
		return 2;
	}

	protected final String getViewId() {
		return getFacesInstance().getViewRoot().getViewId();
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
