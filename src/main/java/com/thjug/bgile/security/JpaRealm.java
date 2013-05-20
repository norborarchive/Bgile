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
package com.thjug.bgile.security;

import com.google.inject.Inject;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.facade.AccountFacade;
import com.thjug.bgile.interceptor.Logging;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.NoResultException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*
* @author Wasan Anusornhirunkarn, @tone
*/
public class JpaRealm extends AuthorizingRealm {

	@Inject
	private AccountFacade accountFacade;

	@Override
    @Logging
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
        final Account account = principals.oneByType(Account.class);
        final Set<String> roleValues = new HashSet<>();
        roleValues.add(String.valueOf(account.getTypeid()));

        final AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roleValues);
        return authorizationInfo;
    }

	@Override
	@Logging
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token)
			throws AuthenticationException {
		final Account account;
		final String credential;
		try {
			if (token instanceof UsernamePasswordToken) {
				final UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
				account = accountFacade.findByUsername(usernamePasswordToken.getUsername());

				if (account.getEnable() != 'Y') {
					throw new LockedAccountException();
				}

				credential = account.getPasswd();
			} else {
				throw new Exception("Unknown Token");
			}
		} catch (final LockedAccountException e) {
			throw e;
		} catch (final NoResultException e) {
			throw new UnknownAccountException();
		} catch (final Exception e) {
			throw new AuthenticationException(e);
		}

		return new SimpleAuthenticationInfo(account, credential, JpaRealm.class.getSimpleName());
	}
}
