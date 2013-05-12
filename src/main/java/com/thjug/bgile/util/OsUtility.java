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

import java.util.Locale;

/**
 *
 * @author @nuboat
 */
public final class OsUtility {

	private static final String OS_NAME;
	private static final boolean IS_WINDOW;
	private static final boolean IS_NIX;
	private static final boolean IS_OSX;
	private static final boolean IS_SOLARIS;

	private OsUtility() {
	}

	static {
		OS_NAME = System.getProperty("os.name");

		final String osName = OS_NAME.toLowerCase(Locale.ENGLISH);
		IS_WINDOW = osName.startsWith("windows");
		IS_NIX = osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0;
		IS_OSX = osName.indexOf("mac") >= 0;
		IS_SOLARIS = osName.indexOf("sunos") >= 0;
	}

	public static String getOsName() {
		return OS_NAME;
	}

	public static boolean isWindows() {
		return IS_WINDOW;
	}

	public static boolean isUnix() {
		return IS_NIX;
	}

	public static boolean isMaxOSX() {
		return IS_OSX;
	}

	public static boolean isSolaris() {
		return IS_SOLARIS;
	}

}
