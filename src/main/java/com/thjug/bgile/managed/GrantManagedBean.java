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
package com.thjug.bgile.managed;

import com.google.inject.Inject;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.facade.AccountFacade;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@ViewScoped
public class GrantManagedBean extends AbstractManaged {

	private String accountname;
	private List<Account> accounts;

	@Inject
	private transient AccountFacade facade;

	public List<Account> findAccounts() {
		return facade.containsKeyword(accountname);
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(final String accountname) {
		this.accountname = accountname;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(final List<Account> accounts) {
		this.accounts = accounts;
	}

}
