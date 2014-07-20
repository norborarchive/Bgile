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
package com.thjug.bgile.util;

import java.util.Calendar;
import java.util.TimeZone;
import org.testng.annotations.Test;

/**
 *
 * @author nuboat
 */
public class DateNGTest {

	@Test
	public void testDecimal() {

		final long MILLIS_IN_DAY = 1000L * 60L * 60L * 24L;

		final Calendar startOfTime = Calendar.getInstance();
		startOfTime.setTimeZone(TimeZone.getTimeZone("UTC"));
		startOfTime.clear();
		startOfTime.set(1900, 0, 1, 0, 0, 0);

		final Calendar myDate = Calendar.getInstance();
		myDate.setTimeZone(TimeZone.getTimeZone("UTC"));
		myDate.clear();
		myDate.set(2005, 4, 11, 0, 0, 0);

		final long diff = myDate.getTimeInMillis() - startOfTime.getTimeInMillis(); // + (2 * MILLIS_IN_DAY);
		final double decimalTime = (double) diff / (double) MILLIS_IN_DAY;
		System.out.println(decimalTime);
	}
}
