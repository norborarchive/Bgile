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

import com.thjug.bgile.entity.Account;
import com.thjug.bgile.define.Enable;
import com.thjug.bgile.facade.AccountFacade;

import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;

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

/**
 * 
 * @author @nuboat
 */
public class JpaRealm extends AuthorizingRealm {

	@Inject
	private AccountFacade facade;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		final Account account = principals.oneByType(Account.class);
		final Set<String> roleValues = new HashSet<>();
		roleValues.add(account.getTypeid().getText());

		return new SimpleAuthorizationInfo(roleValues);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token) {

		if (token instanceof UsernamePasswordToken) {
			final UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
			final Account account = facade.findByUsername(usernamePasswordToken.getUsername());

			if (account == null) {
				throw new UnknownAccountException();
			}
			if (account.getEnableid() != Enable.T) {
				throw new LockedAccountException();
			}

			return new SimpleAuthenticationInfo(account, account.getPasswd(), JpaRealm.class.getSimpleName());
		} else {
			throw new AuthenticationException("Invalid Token Type");
		}

	}

}
