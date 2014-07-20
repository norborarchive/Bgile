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

/**
 *
 * @author @nuboat
 */
//@ManagedBean
//@ApplicationScoped
public class LocalizationManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;

	private static final String LOCALE = "th_TH";

	public String getLocale() {
		return LOCALE;
	}

}
