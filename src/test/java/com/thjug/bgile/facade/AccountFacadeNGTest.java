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
import com.thjug.bgile.facade.AccountFacade;
import java.util.Date;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.thjug.bgile.entity.Account;

/**
 *
 * @author @nuboat
 */
public class AccountFacadeNGTest extends AbstractFacadeNGTest {

	@Test
	public void FindByAccountid() throws Exception {
		final Account account;
		final AccountFacade accountFacadeImpl = injector.getInstance(AccountFacade.class);

		account = accountFacadeImpl.findById(1);
		assertEquals(account.getId(), Integer.valueOf(1));
	}

	@Test
	public void FindByUserNameCaseAccountFound() throws Exception {
		final Account account;
		final String username = "admin";
		final AccountFacade accountFacadeImpl = injector.getInstance(AccountFacade.class);

		account = accountFacadeImpl.findByUsername(username);
		assertNotNull(account);
	}

	@Test
	public void CreateAccountCaseCreateSuccess() {
		final String username = Long.toString(new Date().getTime());

		final Account account = new Account();
		account.setTypeid(Accounttype.S);
		account.setEnableid(Enable.T);

		final AccountFacade accountFacadeImpl = injector.getInstance(AccountFacade.class);
		accountFacadeImpl.createAccount(account);

		final Account persistedAccount = accountFacadeImpl.findByUsername(username);
		assertNotNull(persistedAccount);
	}
}
