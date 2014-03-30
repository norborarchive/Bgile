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
package com.thjug.bgile.facade;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author nuboat
 */
public class PropertyFacadeNGTest extends AbstractFacadeNGTest {

	@Test
	public void testGetString() {
		final String id = "mail.smtp.host";
		final PropertyFacade instance = injector.getInstance(PropertyFacade.class);

		final String expResult = "smtp.live.com";
		final String result = instance.getString(id);
		Assert.assertEquals(result, expResult);
	}

	@Test
	public void testGetStringCaseNotFound() {
		final String id = "notfound";
		final PropertyFacade instance = injector.getInstance(PropertyFacade.class);

		final String result = instance.getString(id);
		Assert.assertNull(result);
	}

}
