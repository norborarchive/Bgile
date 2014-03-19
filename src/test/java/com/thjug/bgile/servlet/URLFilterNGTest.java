/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.bgile.servlet;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.mockito.Matchers.anyString;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.Test;

/**
 * 
 * @author nuboat
 */
public class URLFilterNGTest {

	final URLFilter instance = new URLFilter();

	@Test
	public void testInit() throws Exception {
		final FilterConfig filterConfig = null;
		instance.init(filterConfig);
	}

	@Test
	public void testDoFilterCasePassFilterChain1() throws Exception {
		final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		final FilterChain chain = Mockito.mock(FilterChain.class);

		when(request.getServletPath()).thenReturn("homeloan.xhtml");
		instance.doFilter(request, response, chain);

		verify(chain).doFilter(request, response);
	}

	@Test
	public void testDoFilterCasePassFilterChain2() throws Exception {
		final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		final FilterChain chain = Mockito.mock(FilterChain.class);

		when(request.getServletPath()).thenReturn("base.css");
		instance.doFilter(request, response, chain);

		verify(chain).doFilter(request, response);
	}

	@Test
	public void testDoFilterCasePassFilterChain3() throws Exception {
		final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		final FilterChain chain = Mockito.mock(FilterChain.class);

		when(request.getServletPath()).thenReturn("jquery.js");
		instance.doFilter(request, response, chain);

		verify(chain).doFilter(request, response);
	}

	@Test
	public void testDoFilterCasePassFilterChain4() throws Exception {
		final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		final FilterChain chain = Mockito.mock(FilterChain.class);

		when(request.getServletPath()).thenReturn("/assets/....");
		instance.doFilter(request, response, chain);

		verify(chain).doFilter(request, response);
	}

	@Test
	public void testDoFilterCasePassFilterChain5() throws Exception {
		final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		final FilterChain chain = Mockito.mock(FilterChain.class);

		when(request.getServletPath()).thenReturn("/javax.faces.resource/...");
		instance.doFilter(request, response, chain);

		verify(chain).doFilter(request, response);
	}

	@Test
	public void testDoFilterCaseDispatcherRequest() throws Exception {
		final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		final FilterChain chain = Mockito.mock(FilterChain.class);

		when(request.getServletPath()).thenReturn("/homeloan/...");
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		instance.doFilter(request, response, chain);

		verify(request).getRequestDispatcher(anyString());
		verify(dispatcher).forward(request, response);
	}

	@Test
	public void testDestroy() {
		instance.destroy();
	}
}