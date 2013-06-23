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
package com.thjug.bgile.service;

import com.thjug.bgile.entity.Account;
import javax.persistence.NoResultException;

/**
 *
 * @author @nuboat
 */
public final class AccountService extends AbstractService<Account> {

	public AccountService() {
		super(Account.class);
	}

	public Account findByUsername(final String username) {
		try {
			return findOne(Account.FIND_BY_USERNAME, username.toUpperCase());
		} catch (final NoResultException e) {
			return null;
		}
	}

}
