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

import java.util.List;

import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.AuthenSession;
import java.util.UUID;

/**
 * 
 * @author @nuboat
 */
public final class AuthenSessionService extends AbstractService<AuthenSession> {

	public AuthenSessionService() {
		super(AuthenSession.class);
	}

	public AuthenSession find(final String id) {
		return findOne(AuthenSession.FIND_BY_ID, id);
	}

	public List<AuthenSession> find(final Account account) {
		return findAll(AuthenSession.FIND_BY_ACCOUNT, account);
	}

	public AuthenSession create(final Account account, final boolean isRemberMe) {
		final AuthenSession authenSession = new AuthenSession();
		final String uuid = UUID.randomUUID().toString();

		authenSession.setId(uuid.replace("-", ""));
		authenSession.setAccount(account);
		authenSession.setRememberMe(isRemberMe);
		authenSession.setUpdateby(account.getId());
		return create(authenSession);
	}

}
