/*
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
 */
package com.thjug.bgile.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.testng.Assert.assertNotEquals;
import org.testng.annotations.Test;

/**
 * 
 * @author @nuboat
 */
public class EncrypterNGTest {

	private static final Logger LOG = LoggerFactory.getLogger(EncrypterNGTest.class);

	@Test
	public void testCipher() {
		final String plain = "password";
		final String result = Encrypter.cipher(plain);
		assertNotEquals(result, plain);

		LOG.info("Cypher : {} ", result);
	}

}