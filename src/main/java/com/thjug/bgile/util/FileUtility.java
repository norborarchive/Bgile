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

/**
 *
 * @author @nuboat
 */
public final class FileUtility {

	private FileUtility() {
	}

	public static File getFile(final String[] fullPath) {
		return new File(buildFullPath(fullPath));
	}

	private static String getSubPath(final String s) {
		return s.replace(File.separator, Constants.EMPTY).replace(File.pathSeparator, Constants.EMPTY);
	}

	private static String buildFullPath(final String[] fullPath) {
		final StringBuilder path;
		int index = 0;
		if (OsUtility.isWindows()) {
			path = new StringBuilder(getSubPath(fullPath[index++]));
			path.append(File.pathSeparator).append(File.separator);
		} else {
			path = new StringBuilder(File.separator);
		}
		for (final String fragment : fullPath) {
			path.append(getSubPath(fragment)).append(File.separator);
		}
		return path.toString();
	}

}
