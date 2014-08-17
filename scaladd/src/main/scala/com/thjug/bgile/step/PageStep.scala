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

import com.thjug.bgile.page.Bgile;
import com.thjug.bgile.webdriver.DefaultWebDriverProvider;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.testng.Assert;

/**
 *
 * @author nuboat
 */
class PageStep(p:DefaultWebDriverProvider) {

  val bgile = new Bgile(p)

  @Given("user access $pageurl page")
  def access(pageurl: String) {
    bgile.go(pageurl)
  }

  @When("user enter $input into $elementid")
  def enterText(input: String , elementid: String) {
    bgile.getElementById(elementid).sendKeys(input)
  }

  @When("user click $elementid")
  def click(elementid: String ) {
    bgile.getElementById(elementid).click()
  }

  @When("wait for $sec")
  def waitForSeconds(sec: Int){
    Thread.sleep(1000 * sec)
  }

  @Then("system display page as $pagename page")
  def displayPage(pagename: String) {
    val url = bgile.getCurrentUrl().split(";")(0)
    Assert.assertTrue(url.endsWith(pagename))
  }

  @Then("system display title as $title")
  def displayTitle(title: String) {
    Assert.assertEquals(bgile.getTitle(), title);
  }

  @Then("system display top-link as $linkcsv")
  def displayTopLink(linkcsv: String) {
    val links = linkcsv.split(",");
    val element = bgile.getElementByClass("ul.nav");
    Assert.assertEquals(links, element.getText().split("\n\n"));
  }

  @Then("system display current link as $link")
  def displayCurrentLink(link: String) {
    val element = bgile.getElementByClass("li.current-page");
    Assert.assertEquals(link, element.getText().trim());
  }

}
