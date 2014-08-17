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
package com.thjug.bgile.webdriver;

import java.util.Arrays;
import org.jbehave.web.selenium.PropertyWebDriverProvider;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author nuboat
 */
public class DefaultWebDriverProvider extends PropertyWebDriverProvider {

	@Override
	protected ChromeDriver createChromeDriver() {
		final DesiredCapabilities dc = DesiredCapabilities.chrome();
		final String[] cmdOptions = {"--ignore-certificate-errors"};
		dc.setCapability("chrome.switches", Arrays.asList(cmdOptions));

		final ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		dc.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(dc);
	}

}
