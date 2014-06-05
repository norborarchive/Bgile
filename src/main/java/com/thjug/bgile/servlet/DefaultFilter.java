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
package com.thjug.bgile.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author @nuboat
 */
public abstract class DefaultFilter implements Filter {

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	protected boolean isBypassFilter(final String servletpath) {
		final boolean isBypassFilter = servletpath.endsWith(".js")
				|| servletpath.endsWith(".ico")
				|| servletpath.endsWith(".css")
				|| servletpath.contains("assets")
				|| servletpath.contains("servlet_")
				|| servletpath.contains("javax.faces.resource");

		return isBypassFilter;
	}

	protected void redirectToDashboard(final ServletRequest request, final ServletResponse response) throws IOException {
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		final HttpServletRequest httpRequest = (HttpServletRequest) request;

		httpResponse.sendRedirect(httpRequest.getContextPath() + "/dashboard");
	}
}
