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

import com.thjug.bgile.define.Accounttype;
import com.thjug.bgile.define.Enable;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.security.Encrypter;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

/**
 * 
 * @author @nuboat
 */
public class AccountFacadeNGTest extends AbstractFacadeNGTest {

	@Test
	public void FindByAccountid() throws Exception {
		final Account account;
		final AccountFacade accountFacadeImpl = injector.getInstance(AccountFacade.class);

		account = accountFacadeImpl.findById(2);
		assertEquals(account.getId(), Integer.valueOf(2));
	}

	@Test
	public void FindByUserNameCaseAccountFound() throws Exception {
		final Account account;
		final String username = "nuboat";
		final AccountFacade accountFacadeImpl = injector.getInstance(AccountFacade.class);

		account = accountFacadeImpl.findByUsername(username);
		assertNotNull(account);
	}

	@Test
	public void CreateAccountCaseCreateSuccess() {
		final String username = "norbor";

		final Account account = new Account();
		account.setFirstname("Norbor");
		account.setLastname("Thjug");
		account.setUsername("norbor");
		account.setPasswd(Encrypter.cipher("password"));
		account.setTypeid(Accounttype.S);
		account.setEnableid(Enable.T);
		account.setEmail("norborcity@gmail.com");

		final AccountFacade accountFacadeImpl = injector.getInstance(AccountFacade.class);
		accountFacadeImpl.createAccount(account);

		final Account persistedAccount = accountFacadeImpl.findByUsername(username);
		assertNotNull(persistedAccount);
	}
}
