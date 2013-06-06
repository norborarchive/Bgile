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

	@Logging
	@Transactional
	@Override
	public Account editAccount(final Account account) {
		return accountService.edit(account);
	}

	@Logging
	@Transactional
	@Override
	public void removeAccount(final Account account) throws Exception {
		accountService.remove(account);
	}

	@Logging
	@Transactional
	@Override
	public Account findByUsername(final String username) throws Exception {
		return accountService.findByUsername(username);
	}

	@Logging
	@Transactional
	@Override
	public Account findById(final Integer id) {
		return accountService.find(id);
	}

}
