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

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.persist.Transactional;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.interceptor.Logging;

/**
*
* @author Wasan Anusornhirunkarn, @tone
*/
public class AuthenticationListenerImpl implements AuthenticationListener {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationListenerImpl.class);

	@Logging
	@Override
	@Transactional
	public void onSuccess(final AuthenticationToken token, final AuthenticationInfo info) {
		final Account account = (Account) info.getPrincipals().getPrimaryPrincipal();
		try {
			LOG.info("Account {} id {} login success.", account.getId(), account.getUsername());
		} catch (final Exception e) {
			LOG.warn(e.getMessage(), e);
		}
	}

	@Override
	public void onFailure(final AuthenticationToken token, final AuthenticationException ae) {

	}

	@Logging
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
