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

import javax.inject.Inject;
import com.google.inject.persist.Transactional;

import com.thjug.bgile.service.AccountService;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.interceptor.Logging;

/**
 *
 * @author @nuboat
 */
public class AccountFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	private AccountService accountService;

	@Logging
	@Transactional
	public Account createAccount(final Account account) {
		return accountService.create(account);
	}

	@Logging
	@Transactional
	public Account editAccount(final Account account) {
		return accountService.update(account);
	}

	@Logging
	@Transactional
	public void removeAccount(final Account account) {
		accountService.remove(account);
	}

	@Logging
	@Transactional
	public Account findById(final Integer id) {
		return accountService.find(id);
	}

	@Logging
	@Transactional
	public Account findByUsername(final String username) {
		return accountService.find(username);
	}

	@Logging
	@Transactional
	public List<Account> containsKeyword(final String keyword) {
		return accountService.findAccountList(keyword);
	}

}
