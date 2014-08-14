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
package com.thjug.bgile.step;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.web.selenium.WebDriverProvider;

/**
 *
 * @author nuboat
 */
public class TemplateStep {

	private final WebDriverProvider provider;

	public TemplateStep(final WebDriverProvider provider) {
		this.provider = provider;
	}

	@Given("user access $page page")
	public void userAccess(final String page) {

	}

	@Then("system display $page page")
	public void systemDisplay(final String page) {

	}

	@Then("system display title as $title")
	public void systemTitle(final String title) {

	}

	@Then("wait for $sec")
	public void waitForSeconds(int sec) throws InterruptedException {
		Thread.sleep(1000 * sec);
	}

}
