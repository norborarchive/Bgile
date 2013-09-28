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
import java.util.LinkedList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

/**
 *
 * @author @nuboat
 */
@Singleton
public final class URLFilter extends DefaultFilter {

	private static final Logger LOG = LoggerFactory.getLogger(URLFilter.class);

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		final String servletpath = httpServletRequest.getServletPath();
		if (servletpath.contains("javax.faces.resource")) {
			chain.doFilter(request, response);
			return;
		}
		LOG.debug("request: {}", servletpath);

		if (servletpath.contains(".xhtml") || servletpath.contains(".js") || servletpath.contains(".css")
				|| servletpath.contains("assets")) {

			chain.doFilter(request, response);
		} else {

			final List<String> attributes = new LinkedList<>();
			for (final String attribute : servletpath.split("/")) {
				if (!attribute.trim().isEmpty()) {
					attributes.add(attribute);
				}
			}
			final String destination = "/" + attributes.get(0).split(";")[0] + ".xhtml";
			request.setAttribute("ATTRIBUTES", attributes);

			LOG.info("Dispatch {} to {}", servletpath, destination);
			httpServletRequest.getRequestDispatcher(destination).forward(request, response);
		}

	}

}
