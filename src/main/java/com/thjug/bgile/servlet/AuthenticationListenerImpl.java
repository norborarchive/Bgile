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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import com.thjug.bgile.entity.Account;
import com.thjug.bgile.facade.AuthenSessionFacade;

/**
*
* @author @nuboat
*/
public class AuthenticationListenerImpl implements AuthenticationListener {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationListenerImpl.class);

	@Inject
	private transient AuthenSessionFacade facade;

	@Override
	@Transactional
	public void onSuccess(final AuthenticationToken token, final AuthenticationInfo info) {
		final Account account = (Account) info.getPrincipals().getPrimaryPrincipal();
		try {
			facade.saveSession(account, SecurityUtils.getSubject().isRemembered());

			LOG.info("Account {} id {} login success.", account.getId(), account.getUsername());
		} catch (final Exception e) {
			LOG.warn(e.getMessage(), e);
		}
	}

	@Override
	public void onFailure(final AuthenticationToken token, final AuthenticationException ae) {

	}

	@Override
	@Transactional
	public void onLogout(final PrincipalCollection principals) {
		final Account account = (Account) principals.getPrimaryPrincipal();
		try {
			LOG.info("Account {} id {} logout success.", account.getId(), account.getUsername());
		} catch (final Exception e) {
			LOG.warn(e.getMessage(), e);
		}
	}

}
