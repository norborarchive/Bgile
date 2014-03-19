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

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 * 
 * @author @nuboat
 */
public class FileUtilityNGTest {

	@Test
	public void testPrivateConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException,
			InvocationTargetException {

		final Constructor constructor = FileUtility.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testGetFile() {
		final String[] fullPath = { "Applications", "Mail.app", };
		final File result = FileUtility.getFile(fullPath);
		final boolean expResult = true;
		assertEquals(result.exists(), expResult);
	}

}