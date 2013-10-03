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
import com.timgroup.jgravatar.Gravatar;
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

	private final String gravatarUrl;
	private final transient Gravatar gravatar = new Gravatar();

	/**
	 * gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
	 * gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);
	 */
	public AccountManaged() {
		gravatarUrl = (getPrincipal() != null) ? gravatar.getUrl(getPrincipal().getEmail()) : null;
	}

	public String getGravatarUrl() {
		return gravatarUrl;
	}

	public Account getAccount() {
		return getPrincipal();
	}

	public boolean isViewonly() {
		return (getPrincipal() != null && getPrincipal().getId() == 2) ? false : true;
	}

}
