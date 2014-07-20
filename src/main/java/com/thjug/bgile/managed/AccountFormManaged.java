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

import com.thjug.bgile.define.Accounttype;
import com.thjug.bgile.define.Enable;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.facade.AccountFacade;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
//@ViewScoped
//@ManagedBean(name = "accountForm")
public class AccountFormManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(AccountFormManaged.class);

	private Account account;

	@Inject
	private transient AccountFacade accountFacade;

	public AccountFormManaged() {
		account = new Account();
		account.setTypeid(Accounttype.A);
		account.setEnableid(Enable.T);
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

	// FIXME
	public void handleFileUpload(final FileUploadEvent event) {
		final UploadedFile file = event.getFile();
		LOG.info("File: ", file.getFileName());
	}

	public String accountList() {
		return redirect("/admin/account");
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(final Account account) {
		this.account = account;
	}
}
