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
import com.thjug.bgile.security.Encrypter;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@ViewScoped
public final class ProfileManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ProfileManaged.class);
	private Account account;
	private String viewid;
	private String password;
	private String confirmpassword;
	@Inject
	private transient AccountFacade facade;

	@PostConstruct
	public void initial() {
		try {
			account = facade.findById(getLoginId());
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public String linkToProfile(final String viewid) {
		this.viewid = viewid;
		LOG.info("Save viewid : {}", viewid);
		return redirect("profile");
	}

	public String back() {
		return (viewid != null) ? redirect(viewid) : redirect("dashboard");
	}

	public String save() {
		try {
			facade.editAccount(account);
		} catch (final Exception e) {
			addErrorMessage("Server Error", "Cannot changed account information.");
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public String changepasswd() {
		try {
			if (password.equals(confirmpassword)) {
				account = facade.findById(getLoginId());
				account.setPasswd(Encrypter.cipher(password));
				facade.editAccount(account);
			} else {
				addWarnMessage("Password Mismatch", "Verify password & confirm password not equal.");
			}
		} catch (final Exception e) {
			addErrorMessage("Server Error", "Cannot changed account password.");
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(final Account account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(final String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

}
