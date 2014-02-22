package com.thjug.bgile.servlet;

import com.google.inject.Singleton;
import java.io.IOException;

import javax.servlet.FilterChain;
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
@Singleton
public class RememberFilter extends DefaultFilter {

	private static final Logger LOG = LoggerFactory.getLogger(RememberFilter.class);

	/**
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 * @param chain
	 *            The filter chain we are processing
	 * 
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet error occurs
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final String servletpath = httpRequest.getServletPath();

		if (isBypassFilter(servletpath)) {
			chain.doFilter(request, response);
			return;
		}
		LOG.debug("request: {}", servletpath);

		chain.doFilter(request, response);
	}

}
