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
package com.thjug.bgile.guice;

import java.util.Collection;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.guice.web.ShiroWebModule;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.mgt.WebSecurityManager;

import com.google.inject.Exposed;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import com.google.inject.util.Types;
import static org.apache.shiro.guice.web.ShiroWebModule.AUTHC;
import static org.apache.shiro.guice.web.ShiroWebModule.ROLES;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.security.JpaRealm;
import com.thjug.bgile.security.ShiroWebSecurityManager;
import com.thjug.bgile.servlet.AuthenticationListenerImpl;

/**
*
* @author Wasan Anusornhirunkarn, @tone
*/
public class ShiroWebModuleImpl extends ShiroWebModule {

	public ShiroWebModuleImpl(final ServletContext servletContext) {
		super(servletContext);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void configureShiroWeb() {
		bindRealm().to(JpaRealm.class);
		bindAuthenticationListener().to(AuthenticationListenerImpl.class);
		bindConstant().annotatedWith(Names.named("shiro.loginUrl")).to("/signin.xhtml");
		bindConstant().annotatedWith(Names.named("shiro.unauthorizedUrl")).to("/unauthorized.xhtml");
		addFilterChain("/signin.xhtml", Key.get(PassThruAuthenticationFilter.class));
		addFilterChain("/home.xhtml", Key.get(PassThruAuthenticationFilter.class));
		addFilterChain("/**", AUTHC);
		addFilterChain("/admin/**", AUTHC, config(ROLES, "admin"));
		bind(authenticationListenerCollectionKey()).to(authenticationListenerSetKey());
	}

	@Override
	protected void bindWebSecurityManager(AnnotatedBindingBuilder<? super WebSecurityManager> bind) {
		try {
			bind.toConstructor(ShiroWebSecurityManager.class.getConstructor(Collection.class, Collection.class))
					.asEagerSingleton();
		} catch (NoSuchMethodException e) {
			throw new ConfigurationException(
					"This really shouldn't happen.  Either something has changed in Shiro, or there's a bug in ShiroModule.",
					e);
		}
	}

	protected final LinkedBindingBuilder<AuthenticationListener> bindAuthenticationListener() {
		Multibinder<AuthenticationListener> multibinder = Multibinder.newSetBinder(binder(),
				AuthenticationListener.class);
		return multibinder.addBinding();
	}

	@SuppressWarnings( { "unchecked" })
	private Key<Set<AuthenticationListener>> authenticationListenerSetKey() {
		return (Key<Set<AuthenticationListener>>) Key.get(TypeLiteral.get(Types.setOf(AuthenticationListener.class)));
	}

	@SuppressWarnings( { "unchecked" })
	private Key<Collection<AuthenticationListener>> authenticationListenerCollectionKey() {
		return (Key<Collection<AuthenticationListener>>) Key.get(Types.newParameterizedType(Collection.class,
				AuthenticationListener.class));
	}

	@Provides
	@Exposed
	public Account provideAccount() {
		final Account account = (Account) SecurityUtils.getSubject().getPrincipal();
		return account;
	}
}
