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

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thjug.bgile.entity.Account;
import com.thjug.bgile.facade.AccountFacade;

import com.google.inject.Inject;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@ViewScoped
public class AccountFormManaged extends AbstractManaged {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(AccountFormManaged.class);

	@Inject
	private AccountFacade accountFacade;

	private Account account;

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(final UploadedFile file) {
		this.file = file;
	}

	public AccountFormManaged() {
		account = new Account();
		//account.setAccountAuth(new AccountAuth());
		account.setTypeid('A');
		//account.getAccountauth().setEnable('Y');
	}

	@PostConstruct
	public void init() {
		final Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		final String accountIdParam = params.get("account.id");
		if (accountIdParam != null) {
			final Integer accountId = Integer.parseInt(accountIdParam);
			try {
				account = accountFacade.findById(accountId);
			} catch (Exception e) {
				LOG.warn(e.getMessage());
			}
		}
	}

	public String saveAccount() {
		if (account.getId() == null) {
			try {
				accountFacade.createAccount(account);
			} catch (final Exception e) {
				LOG.warn(e.getMessage());
			}
		} else {
			try {
				accountFacade.editAccount(account);
			} catch (final Exception e) {
				LOG.warn(e.getMessage());
			}
		}
		return null;
	}

	public void handleFileUpload(final FileUploadEvent event) {
		//UploadedFile file = event.getFile();
	}

	public String accountList() {
		return "/admin/account?faces-redirect=true";
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(final Account account) {
		this.account = account;
	}

}
