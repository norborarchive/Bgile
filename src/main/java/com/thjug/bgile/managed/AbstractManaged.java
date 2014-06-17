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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author @nuboat
 */
public abstract class AbstractManaged implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(AbstractManaged.class);

	protected FacesContext getFacesInstance() {
		return FacesContext.getCurrentInstance();
	}

	protected ExternalContext getExternalContext() {
		return getFacesInstance().getExternalContext();
	}

	protected ServletContext getServletContext() {
		return (ServletContext) getExternalContext().getContext();
	}

	protected String getContextPath() {
		return getServletContext().getContextPath();
	}

	protected String getRequestServletPath() {
		return getExternalContext().getRequestServletPath();
	}

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	protected String getRequestURL() {
		return getRequest().getRequestURL().toString();
	}

	protected HttpSession getSession() {
		return (HttpSession) getExternalContext().getSession(true);
	}

	@SuppressWarnings("unchecked")
	protected List<String> getAttribute(final String key) {
		return (List<String>) getRequest().getAttribute(key);
	}

	protected Map<String, String> getParams() {
		return getExternalContext().getRequestParameterMap();
	}

	protected void setParam(final String key, final String value) {
		getParams().put(key, value);
	}

	protected String getParam(final String key) {
		return getParams().get(key);
	}

	protected String redirect(final String page) {
		return page + "?faces-redirect=true";
	}

	protected void setRedirect(final String page) {
		try {
			getExternalContext().redirect(getServletContext().getContextPath() + "/" + page);
		} catch (final IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	protected void putCookieValue(final String cookieName, final String value) {
		final Cookie userCookie = new Cookie(cookieName, value);
		userCookie.setMaxAge(31_536_000);
		getResponse().addCookie(userCookie);
	}

	protected Object getCookieValue(final String cookieName) {
		return getExternalContext().getRequestCookieMap().get(cookieName);
	}

	protected String getViewId() {
		return getFacesInstance().getViewRoot().getViewId();
	}

	protected void addWarnMessage(final String topic, final String message) {
		getFacesInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, topic, message));
	}

	protected void addInfoMessage(final String topic, final String message) {
		getFacesInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, topic, message));
	}

	protected void addErrorMessage(final String topic, final String message) {
		getFacesInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, topic, message));
	}

	/**
	 * 
	 * @param formid
	 *            formid:inputid
	 * @param topic
	 *            bold message
	 * @param message
	 *            regular message
	 */
	protected void addWarnMessage(final String formid, final String topic, final String message) {
		getFacesInstance().addMessage(formid, new FacesMessage(FacesMessage.SEVERITY_WARN, topic, message));
	}

	protected void addInfoMessage(final String formid, final String topic, final String message) {
		getFacesInstance().addMessage(formid, new FacesMessage(FacesMessage.SEVERITY_INFO, topic, message));
	}

	protected void addErrorMessage(final String formid, final String topic, final String message) {
		getFacesInstance().addMessage(formid, new FacesMessage(FacesMessage.SEVERITY_ERROR, topic, message));
	}

}
