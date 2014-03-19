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
package com.thjug.bgile.util;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 * 
 * @author @nuboat
 */
public class StringUtilityNGTest {

	@Test
	public void testIsEmpty1() {
		final String string = "";
		final boolean expResult = true;
		final boolean result = StringUtility.isEmpty(string);
		assertEquals(result, expResult);
	}

	@Test
	public void testIsEmpty2() {
		final String string = null;
		final boolean expResult = true;
		final boolean result = StringUtility.isEmpty(string);
		assertEquals(result, expResult);
	}

	@Test
	public void testIsEmpty3() {
		final String string = "not null";
		final boolean expResult = false;
		final boolean result = StringUtility.isEmpty(string);
		assertEquals(result, expResult);
	}

	@Test
	public void testIsEmail1() {
		final String string = "nuboat@gmail.com";
		final boolean expResult = true;
		final boolean result = StringUtility.isEmail(string);
		assertEquals(result, expResult);
	}

	@Test
	public void testIsEmai2() {
		final String string = "nuboat";
		final boolean expResult = false;
		final boolean result = StringUtility.isEmail(string);
		assertEquals(result, expResult);
	}

	@Test
	public void testIsAlphabet() {
		final String string = "nuboat";
		final boolean expResult = true;
		final boolean result = StringUtility.isAlphabet(string);
		assertEquals(result, expResult);
	}

}
