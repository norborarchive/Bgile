/*
 * <pre>
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
 * </pre>
 */
package com.thjug.bgile.managed;

import com.thjug.bgile.util.Constants;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author @nuboat
 */
@SessionScoped
@ManagedBean(name = "menu")
public class MenuManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;

	private static final String CURRENT_PAGE_CSS_CLASS = "current-page";

	public String getActiveClass(final String page) {
		return getViewId().contains(page) ? CURRENT_PAGE_CSS_CLASS : Constants.EMPTY;
	}

}
