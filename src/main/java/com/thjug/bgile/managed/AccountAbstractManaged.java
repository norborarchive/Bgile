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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 
 * @author @nuboat
 */
public abstract class AccountAbstractManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;

	protected Account getPrincipal() {
		final Subject subject = SecurityUtils.getSubject();
		return (Account) subject.getPrincipal();
	}

}
