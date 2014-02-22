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
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.BoardAccount;
import com.thjug.bgile.interceptor.Logging;
import com.thjug.bgile.service.BoardAccountService;

/**
 * 
 * @author @nuboat
 */
public class BoardAccountFacade {

	@Inject
	private BoardAccountService baService;

	@Logging
	@Transactional
	public List<BoardAccount> findAllByAccount(final Account account) {
		return baService.findBoardAccountByAccount(account);
	}

}
