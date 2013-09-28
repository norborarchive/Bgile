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

	public static File getFile(final String[] fullPath) {
		return new File(buildFullPath(fullPath));
	}

	private static String cleanPath(final String s) {
		return s.replace(File.separator, Constants.EMPTY).replace(File.pathSeparator, Constants.EMPTY);
	}

	/**
	 *
	 * WARNING: Does not supprot Windows.
	 * if (OsUtility.isWindows()) {
	 *		path = new StringBuilder(getSubPath(fullPath[index++]));
	 *		path.append(File.pathSeparator).append(File.separator);
	 * }
	 * @param fullPath : {"home", "nuboat", "media", "avatar"}
	 * @return /home/nuboat/media/avatar
	 */
	private static String buildFullPath(final String[] fullPath) {
		final StringBuilder path = new StringBuilder();
		for (final String fragment : fullPath) {
			path.append(File.separator).append(cleanPath(fragment));
		}
		return path.toString();
	}

	private FileUtility() {
	}

}
