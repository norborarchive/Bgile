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
import java.util.List;

/**
 * 
 * @author @nuboat
 */
public final class AccountService extends AbstractService<Account> {

	private static final int OFFSET = 0;
	private static final int LENGTH = 12;

	public AccountService() {
		super(Account.class);
	}

	public Account find(final String username) {

		return findOne(Account.FIND_BY_USERNAME, username.toUpperCase());
	}

	public List<Account> findAccountList(final String keyword) {

		return findRange(Account.FIND_LIKE_KEYWORD, OFFSET, LENGTH, keyword.toUpperCase() + "%");
	}

}
