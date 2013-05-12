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

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thjug.bgile.faces.PageDataModel;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.facade.AccountFacade;

import com.google.inject.Inject;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@ViewScoped
public class AccountListManaged extends AbstractManaged {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(AccountListManaged.class);
	private final Integer pageSize = 10;

	@Inject
	private AccountFacade accountFacade;
	private PageDataModel<Account> pageDataModel;

	@PostConstruct
	public void init() {
		pageDataModel = new PageDataModel<Account>() {
			private static final long serialVersionUID = 1L;

			@Override
			public List<Account> loadData(int first, int pageSize, String query) {
				List<Account> accounts = null;
				try {
					accounts = accountFacade.findAccounts(first, pageSize);
				} catch (Exception e) {
					LOG.warn(e.getMessage());
				}
				try {
					this.setRowCount(accountFacade.getAccountsCount());
				} catch (Exception e) {
					LOG.warn(e.getMessage());
				}
				return accounts;
			}

		};
		pageDataModel.setPageSize(pageSize);
		pageDataModel.load(1, null);
	}

	public String editAccount() {
		return "/admin/account/form";
	}

	public PageDataModel<Account> getPageDataModel() {
		return pageDataModel;
	}

}
