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

import java.util.List;

import com.google.inject.ImplementedBy;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.facade.impl.AccountFacadeImpl;

/**
 *
 * @author @nuboat
 */
@ImplementedBy(AccountFacadeImpl.class)
public interface AccountFacade {

	public Account createAccount(final Account account) throws Exception;

	public Account editAccount(final Account account) throws Exception;

	public void removeAccount(final Account account) throws Exception;

	public Account findByUsername(final String username) throws Exception;

	public Account findAccountById(final Integer id) throws Exception;

	public List<Account> findAllStaff() throws Exception;

	public List<Account> findAccounts(final Integer offset, final Integer limit) throws Exception;

	public int getAccountsCount() throws Exception;

}
