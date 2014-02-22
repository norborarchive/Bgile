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

import javax.inject.Inject;
import com.google.inject.persist.Transactional;

import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.AuthenSession;
import com.thjug.bgile.interceptor.Logging;
import com.thjug.bgile.service.AuthenSessionService;

/**
 * 
 * @author @nuboat
 */
public class AuthenSessionFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	private AuthenSessionService service;

	@Logging
	@Transactional
	public AuthenSession saveSession(final Account account, final boolean isRemberMe) {
		return service.create(account, isRemberMe);
	}

}
