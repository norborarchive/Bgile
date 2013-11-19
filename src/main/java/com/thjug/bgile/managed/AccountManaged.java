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

import com.thjug.bgile.entity.Account;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author @nuboat
 */
@SessionScoped
@ManagedBean(name = "account")
public final class AccountManaged extends AccountAbstractManaged {

	private static final long serialVersionUID = 1L;

	public String getGravatarUrl() {
		return getSession().getAttribute("GRAVATARURL") == null ? null : getSession().getAttribute("GRAVATARURL")
				.toString();
	}

	public Account getAccount() {
		return getPrincipal();
	}

	public boolean isNotSignin() {
		return getPrincipal() == null;
	}

}
