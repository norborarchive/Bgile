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

import com.thjug.bgile.common.Config;
import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author nuboat
 */
public abstract class Page extends FluentWebDriverPage {

	public static String DEFAULT_URL = "http://localhost:8084/";
	public static String BROWSER_TYPE = "chrome";
	public static final String BROWSER_FIREFOX = "firefox";
	public static final String URL_PARAMETER = "webdriver.base.url";

	static {
		DEFAULT_URL = Config.get("default_url");
		BROWSER_TYPE = Config.get("default_browser");
	}

	protected final WebDriverProvider driverProvider;

	protected final String url;

	protected Page(final WebDriverProvider driverProvider, final String url) {
		super(driverProvider);
		this.driverProvider = driverProvider;
		this.url = url;

	}

	protected WebDriver getWebDriver() {
		return driverProvider.get();
	}

	public String getURL() {
		final String baseUrl = System.getProperty(URL_PARAMETER, DEFAULT_URL);
		return (url == null) ? baseUrl : String.format("%s%s", baseUrl, url);
	}

	public void go() {
		getWebDriver().get(getURL());
	}

}
