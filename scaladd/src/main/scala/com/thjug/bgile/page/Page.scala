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

import org.jbehave.web.selenium.{FluentWebDriverPage, WebDriverProvider}
import org.openqa.selenium.By.{cssSelector, id}
import org.openqa.selenium.{WebDriver, WebElement};

/**
 *
 * @author nuboat
 */
abstract class Page(driverProvider: WebDriverProvider) extends FluentWebDriverPage(driverProvider: WebDriverProvider) {

  def getWebDriver(): WebDriver = {
    return getDriverProvider().get();
  }

  def go(pageurl: String) {
    getWebDriver().get("http://localhost:8084" + pageurl);
  }

  def getElementById(elementId: String): WebElement = {
    return findElement(id(elementId));
  }

  def getElementByClass(cssClass: String): WebElement = {
    return findElement(cssSelector(cssClass));
  }

}
