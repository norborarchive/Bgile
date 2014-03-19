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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 * 
 * @author @nuboat
 */
public class OsUtilityNGTest {

	@Test
	public void testPrivateConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException,
			InvocationTargetException {

		final Constructor constructor = OsUtility.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testGetOsName() {
		final String result = OsUtility.getOsName();
		assertNotNull(result);
	}

	@Test
	public void testIsWindows() {
		final boolean expResult = false;
		final boolean result = OsUtility.isWindows();
		assertEquals(result, expResult);
	}

	@Test
	public void testIsUnix() {
		final boolean expResult = false;
		final boolean result = OsUtility.isUnix();
		assertEquals(result, expResult);
	}

	@Test
	public void testIsMaxOSX() {
		final boolean expResult = true;
		final boolean result = OsUtility.isMaxOSX();
		assertEquals(result, expResult);
	}

	@Test
	public void testIsSolaris() {
		final boolean expResult = false;
		final boolean result = OsUtility.isSolaris();
		assertEquals(result, expResult);
	}
}