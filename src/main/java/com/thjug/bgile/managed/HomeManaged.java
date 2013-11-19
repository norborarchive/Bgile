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

import com.thjug.bgile.util.Constants;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@RequestScoped
public final class HomeManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(FboardManaged.class);

	private static final String CURRENT_PAGE = "current-page";

	public boolean isHasSession() {
		try {
			final Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isAuthenticated()) {
				return true;
			}
		} catch (final UnavailableSecurityManagerException e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	public String getActiveClass(final String page) {
		if (getViewId().contains(page)) {
			return CURRENT_PAGE;
		} else {
			return Constants.EMPTY;
		}
	}

}
