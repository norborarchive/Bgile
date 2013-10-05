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

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
public abstract class AbstractManaged implements Serializable {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractManaged.class);

	protected final String getRequestServletPath() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
	}

	protected final HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	protected final String getRequestURL() {
		return getRequest().getRequestURL().toString();
	}

	protected final HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
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

	protected final List<String> getAttribute(final String key) {
		return (List<String>) getRequest().getAttribute(key);
	}

	protected final Map<String, String> getParams() {
		return getExternalContext().getRequestParameterMap();
	}

	protected final void setParam(final String key, final String value) {
		getParams().put(key, value);
	}

	protected final String getParam(final String key) {
		return getParams().get(key);
	}

	protected final String redirect(final String page) {
		return page + "?faces-redirect=true";
	}

	protected final void setRedirect(final String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (final IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	protected final void putCookieValue(final String cookieName, final String value) {
		FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("CookieName", value, null);
	}

	protected final Object getCookieValue(final String cookieName) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get("cookieName");
	}

	protected final String getViewId() {
		return getFacesInstance().getViewRoot().getViewId();
	}

	protected final void addWarnMessage(final String topic, final String message) {
		getFacesInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, topic, message));
	}

	protected final void addInfoMessage(final String topic, final String message) {
		getFacesInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, topic, message));
	}

	protected final void addErrorMessage(final String topic, final String message) {
		getFacesInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, topic, message));
	}

	// "formid:inputid"
	protected final void addWarnMessage(final String formid, final String topic, final String message) {
		getFacesInstance().addMessage(formid, new FacesMessage(FacesMessage.SEVERITY_WARN, topic, message));
	}

	protected final void addInfoMessage(final String formid, final String topic, final String message) {
		getFacesInstance().addMessage(formid, new FacesMessage(FacesMessage.SEVERITY_INFO, topic, message));
	}

	protected final void addErrorMessage(final String formid, final String topic, final String message) {
		getFacesInstance().addMessage(formid, new FacesMessage(FacesMessage.SEVERITY_ERROR, topic, message));
	}

}
