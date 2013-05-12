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
package com.thjug.bgile.facade.impl;

import java.util.Date;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.thjug.bgile.entity.Account;
import java.util.List;

/**
 *
 * @author @nuboat
 */
public class AccountFacadeImplNGTest extends AbstractFacadeNGTest {

	@Test
	public void FindByAccountid() throws Exception {
		final Account account;
		final AccountFacadeImpl accountFacadeImpl = injector.getInstance(AccountFacadeImpl.class);

		account = accountFacadeImpl.findAccountById(1);
		assertEquals(account.getId(), Integer.valueOf(1));
	}

	@Test
	public void FindByTypeidAccountFound() throws Exception {
		final List<Account> accountList;
		final AccountFacadeImpl accountFacadeImpl = injector.getInstance(AccountFacadeImpl.class);

		accountList = accountFacadeImpl.findAllStaff();
		assertNotEquals(accountList.size(), 0);
	}

	@Test
	public void FindByUserNameCaseAccountFound() throws Exception {
		final Account account;
		final String username = "admin";
		final AccountFacadeImpl accountFacadeImpl = injector.getInstance(AccountFacadeImpl.class);

		account = accountFacadeImpl.findByUsername(username);
		assertNotNull(account);
	}

	@Test
	public void CreateAccountCaseCreateSuccess() {
		final String username = Long.toString(new Date().getTime());

		//final AccountAuth accountAuth = new AccountAuth();
		//accountAuth.setUsername(username);
		//accountAuth.setPassword("password");
		//accountAuth.setEnable('N');

		final Account account = new Account();
		account.setTypeid('U');
		//account.setAccountAuth(accountAuth);

		try {
			final AccountFacadeImpl accountFacadeImpl = injector.getInstance(AccountFacadeImpl.class);
			accountFacadeImpl.createAccount(account);

			final Account persistedAccount = accountFacadeImpl.findByUsername(username);

			//assertNotNull(persistedAccount.getAccountauth());
			assertNotNull(persistedAccount);

			accountFacadeImpl.removeAccount(persistedAccount);

		} catch (final Exception e) {
			fail(e.getMessage(), e);
		}
	}
}
