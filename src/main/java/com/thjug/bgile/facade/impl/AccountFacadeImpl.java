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

import com.thjug.bgile.facade.AbstractFacade;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import com.thjug.bgile.service.AccountService;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.facade.AccountFacade;
import com.thjug.bgile.interceptor.Logging;

/**
 *
 * @author @nuboat
 */
public class AccountFacadeImpl implements AccountFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	private transient AccountService accountService;

	@Override
	@Logging
	@Transactional
	public Account createAccount(final Account account) throws Exception {
		return accountService.create(account);
	}

	@Override
	@Logging
	@Transactional
	public Account editAccount(final Account account) {
		return accountService.edit(account);
	}

	@Override
	@Logging
	@Transactional
	public void removeAccount(final Account account) throws Exception {
		accountService.remove(account);
	}

	@Override
	@Logging
	@Transactional
	public Account findByUsername(final String username) throws Exception {
		return accountService.findOne(Account.findByUsername, username);
	}

	@Override
	@Logging
	@Transactional
	public Account findAccountById(final Integer id) {
		return accountService.find(id);
	}

	@Override
	@Logging
	@Transactional
	public List<Account> findAllStaff() throws Exception {
		return accountService.findAll(Account.findByTypeid, 'S');
	}

	@Override
	@Logging
	@Transactional
	public List<Account> findAccounts(final Integer offset, final Integer limit) throws Exception {
		return accountService.findRange(Account.findAll, offset, limit);
	}

	@Override
	@Logging
	@Transactional
	public int getAccountsCount() throws Exception {
		return accountService.<Long> findOne(Account.findAllCount).intValue();
	}
}
