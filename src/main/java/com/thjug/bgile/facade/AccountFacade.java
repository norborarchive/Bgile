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

import com.google.inject.ImplementedBy;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.facade.impl.AccountFacadeImpl;

/**
 *
 * @author @nuboat
 */
@ImplementedBy(AccountFacadeImpl.class)
public interface AccountFacade {

	Account createAccount(final Account account);

	Account editAccount(final Account account);

	void removeAccount(final Account account);

	Account findByUsername(final String username);

	Account findById(final Integer id);

}
