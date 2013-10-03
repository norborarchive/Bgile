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
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Singleton;
import com.google.inject.Inject;
import com.thjug.bgile.define.Private;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.BoardAccount;
import com.thjug.bgile.facade.BoardFacade;
import com.thjug.bgile.facade.GrantFacade;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author @nuboat
 */
@Singleton
public final class BoardPermissionFilter extends DefaultFilter {

	@Inject
	private BoardFacade facade;
	@Inject
	private GrantFacade grant;

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		final Object obj = request.getAttribute("ATTRIBUTES");
		if (obj == null) {
			chain.doFilter(request, response);
			return;
		}

		final List<String> attributes = (List<String>) obj;
		final Integer boardid = Integer.valueOf(attributes.get(1));

		if (!"board".equals(attributes.get(0))) {
			chain.doFilter(request, response);
			return;
		}

		final Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			final Account account = (Account) subject.getPrincipal();
			final BoardAccount boardaccount = grant.getBoardAccount(account.getId(), boardid);
			if (boardaccount != null) {
				final HttpServletRequest httpRequest = (HttpServletRequest) request;
				httpRequest.getSession().setAttribute(BoardAccount.class.getSimpleName(), boardaccount);
				chain.doFilter(request, response);
				return;
			}
		}

		final Board board = facade.findById(boardid);
		if (board.getPrivateid() == Private.F) {
			chain.doFilter(request, response);
		} else {
			redirectToDashboard(request, response);
		}

	}

}
