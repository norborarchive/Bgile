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

import com.thjug.bgile.entity.Account;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
public class AuthenticationListenerImpl implements AuthenticationListener {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationListenerImpl.class);

	@Override
	public void onSuccess(final AuthenticationToken token, final AuthenticationInfo info) {
		final Account account = (Account) info.getPrincipals().getPrimaryPrincipal();
		if (account != null) {
			LOG.info("Account {} id {} login success.", account.getId(), account.getUsername());
		}
	}

	@Override
	public void onFailure(final AuthenticationToken token, final AuthenticationException ae) {
		LOG.info("Account {} login fail.", token.getPrincipal());
	}

	@Override
	public void onLogout(final PrincipalCollection principals) {
		final Account account = (Account) principals.getPrimaryPrincipal();
		if (account == null) {
			return;
		}
		LOG.info("Account {} id {} logout success.", account.getId(), account.getUsername());
	}

}
