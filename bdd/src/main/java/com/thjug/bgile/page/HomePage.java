/**
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
package com.thjug.bgile.page;

import org.jbehave.web.selenium.WebDriverProvider;

/**
 *
 * @author nuboat
 */
public class HomePage extends Page {

	private static final String PATH = "/home";

	public HomePage(final WebDriverProvider driverProvider) {
		super(driverProvider, PATH);
	}

}
