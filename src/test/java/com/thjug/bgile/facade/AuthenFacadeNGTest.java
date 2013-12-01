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
package com.thjug.bgile.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
public class AuthenFacadeNGTest extends AbstractFacadeNGTest {
	private static final Logger LOG = LoggerFactory.getLogger(AuthenFacadeNGTest.class);

	//private final AuthenFacade authenFacade;

	public AuthenFacadeNGTest() {
		//authenFacade = injector.getInstance(AuthenFacade.class);
	}

	//	@Test
	//	public void authenByUsernameCaseAuthenPass() {
	//		LOG.debug("Test AuthenByUsernameCaseAuthenPass");
	//
	//		final String username = "admin";
	//		final String password = "password";
	//
	//		try {
	//			Account account = authenFacade.authenByUsername(username, password);
	//			assertNotNull(account, "Instance account should not be null.");
	//		} catch (Exception e) {
	//			fail(e.getMessage(), e);
	//		}
	//	}
	//
	//	@Test
	//	public void authenByUsernameCaseNoUser() {
	//		LOG.debug("Test AuthenByUsernameCaseNoUser");
	//
	//		final String username = "abcd";
	//		final String password = "password";
	//
	//		try {
	//			Account account = authenFacade.authenByUsername(username, password);
	//			assertNull(account, "can not found username.");
	//		} catch (Exception e) {
	//			fail(e.getMessage(), e);
	//		}
	//	}
	//
	//	@Test
	//	public void authenByUsernameCasePasswordNotCorrect() {
	//		LOG.debug("Test AuthenByUsernameCaseNoUser");
	//
	//		final String username = "admin";
	//		final String password = "1234";
	//
	//		try {
	//			Account account = authenFacade.authenByUsername(username, password);
	//			assertNull(account, "incorrect password.");
	//		} catch (Exception e) {
	//			fail(e.getMessage(), e);
	//		}
	//	}
	//
	//	@Test
	//	public void authenByUsernameCaseBlankUserAndPassword() {
	//		LOG.debug("Test AuthenByUsernameCaseNoUser");
	//
	//		final String username = "";
	//		final String password = "";
	//
	//		try {
	//			Account account = authenFacade.authenByUsername(username, password);
	//			assertNull(account, "Username Or Password not blank.");
	//		} catch (Exception e) {
	//			fail(e.getMessage(), e);
	//		}
	//	}
	//
	//	@Test
	//	public void authenByUsernameCaseUserDisable() {
	//		LOG.debug("Test AuthenByUsernameCaseNoUser");
	//		final String username = "guest";
	//		final String password = "password";
	//
	//		try {
	//			Account account = authenFacade.authenByUsername(username, password);
	//			assertNull(account, "username disabled.");
	//		} catch (Exception e) {
	//			fail(e.getMessage(), e);
	//		}
	//	}
	//
}
