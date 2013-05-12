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

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

/**
*
* @author Wasan Anusornhirunkarn, @tone
*/
public class ShiroWebSecurityManager extends DefaultWebSecurityManager {

	public ShiroWebSecurityManager(Collection<Realm> realms, Collection<AuthenticationListener> listeners) {
		super(realms);
		ModularRealmAuthenticator authenticator = (ModularRealmAuthenticator) this.getAuthenticator();
		authenticator.setAuthenticationListeners(listeners);
	}

}
