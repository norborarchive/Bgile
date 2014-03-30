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

import javax.mail.MessagingException;
import org.testng.annotations.Test;

/**
 *
 * @author nuboat
 */
public class EmailFacadeNGTest extends AbstractFacadeNGTest {

	@Test
	public void testSend() throws MessagingException {
		final EmailFacade instance = injector.getInstance(EmailFacade.class);
		instance.initial();
		instance.send("nuboat@gmail.com","EmailFacadeNGTest","Yoyo");
	}

}
