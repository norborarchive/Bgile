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

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author nuboat
 */
public abstract class Page extends FluentWebDriverPage {

	protected final String url;

	public Page(final WebDriverProvider driverProvider, final String url) {
		super(driverProvider);
		this.url = url;
	}

	public String getURL() {
		return "http://localhost:8084" + url;
	}

	protected WebDriver getWebDriver() {
		return getDriverProvider().get();
	}

	public void go() {
		getWebDriver().get(getURL());
	}

	public WebElement getElementById(final String elementId) {
		return findElement(id(elementId));
	}

	public WebElement getElementByClass(final String cssClass) {
		return findElement(cssSelector(cssClass));
	}

}
