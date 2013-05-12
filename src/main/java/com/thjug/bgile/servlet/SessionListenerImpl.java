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

import javax.faces.bean.ApplicationScoped;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.guice.GuiceInjectorFactory;

/**
*
* @author Wasan Anusornhirunkarn, @tone
*/
@ApplicationScoped
@ManagedBean
public class SessionListenerImpl implements HttpSessionListener {
	private static final Logger LOG = LoggerFactory.getLogger(SessionListenerImpl.class);

	//@Inject
	//private AccountHistoryFacade accountHistoryFacade;
	@Inject
	private transient Provider<EntityManager> provider;

	public SessionListenerImpl() {
		Injector injector = GuiceInjectorFactory.getInjector();
		injector.injectMembers(this);
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//		HttpSession httpSession = se.getSession();
		//		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) httpSession.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		//		if(principalCollection != null) {
		//			Account account = (Account) principalCollection.getPrimaryPrincipal();
		//			EntityManager entityManager = provider.get();
		//			EntityTransaction transaction = entityManager.getTransaction();
		//			transaction.begin();
		//			try {
		//				accountHistoryFacade.recordHistory(account, AccountHistory.Activity.LOGOUT.toString());
		//			} catch (Exception e) {
		//				LOG.warn(e.getMessage());
		//				transaction.rollback();
		//			} finally {
		//				transaction.commit();
		//			}
		//		}
	}

}
