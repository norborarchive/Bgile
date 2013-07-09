package com.thjug.bgile.faces;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author PeerapatAsoktummarun
 */
public final class URLFilters implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(URLFilters.class);

	private FilterConfig config;

	/**
	 *
	 * @param request The servlet request we are processing
	 * @param response The servlet response we are creating
	 * @param chain The filter chain we are processing
	 *
	 * @exception IOException if an input/output error occurs
	 * @exception ServletException if a servlet error occurs
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response,
			final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest httpServletRequest = ((HttpServletRequest)request);
		final String servletpath = httpServletRequest.getServletPath();

		if (servletpath.contains(".xhtml")
				|| servletpath.contains(".js")
				|| servletpath.contains(".css")
				|| servletpath.contains("assets")) {
			chain.doFilter(request, response);
		} else {
			final List<String> attributes = new LinkedList<>();
			for (final String attribute : servletpath.split("/")) {
				if (!attribute.trim().equals("")) {
					attributes.add(attribute);
				}
			}
			final String destination = genUrl(attributes.get(0).split(";")[0]);
			attributes.remove(0);
			request.setAttribute("ATTRIBUTES", attributes);

			LOG.info("Dispatch {} to {}", servletpath, destination);
			httpServletRequest.getRequestDispatcher(destination).forward(request, response);
		}

	}

	private String genUrl(final String viewid) {
		return "/" + viewid + config.getInitParameter("postfix");
	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

	@Override
	public void destroy() {

	}

}
