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
import com.thjug.bgile.page.Page;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 *
 * @author nuboat
 */
public class PageStep {

	private final Page bgile;

	public PageStep(final WebDriverProvider provider) {
		bgile = new Bgile(provider);
	}

	@Given("user access $pageurl page")
	public void access(final String pageurl) {
		bgile.go(pageurl);
	}

	@When("user enter $input into $elementid")
	public void enterText(final String input, final String elementid) {
		bgile.getElementById(elementid).sendKeys(input);
	}

	@When("user click $elementid")
	public void click(final String elementid) {
		bgile.getElementById(elementid).click();
	}

	@When("wait for $sec")
	public void waitForSeconds(int sec) throws InterruptedException {
		Thread.sleep(1000 * sec);
	}

	@Then("system display page as $pagename page")
	public void displayPage(final String pagename) {
		final String url = bgile.getCurrentUrl().split(";")[0];
		Assert.assertTrue(url.endsWith(pagename));
	}

	@Then("system display title as $title")
	public void displayTitle(final String title) {
		Assert.assertEquals(bgile.getTitle(), title);
	}

	@Then("system display top-link as $linkcsv")
	public void displayTopLink(final String linkcsv) {
		final String[] links = linkcsv.split(",");
		final WebElement element = bgile.getElementByClass("ul.nav");
		Assert.assertEquals(links, element.getText().split("\n\n"));
	}

	@Then("system display current link as $link")
	public void displayCurrentLink(final String link) {
		final WebElement element = bgile.getElementByClass("li.current-page");
		Assert.assertEquals(link, element.getText().trim());
	}

}
