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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

/**
 *
 * @author @nuboat
 */
public final class FileUtility {

	private static final String storeFilePathSeparator = ",";
	private static final String[] serverPath = { "home", "tomcat", "highway-upload" };
	private static final String defaultFile = "default";

	private FileUtility() {
	}

	private static String buildFullPath(final String[] fullPath) {
		final StringBuilder path;
		Integer index = 0;
		if (OsUtility.isWindows()) {
			path = new StringBuilder(getSubPath(fullPath[index++]));
			path.append(File.pathSeparator).append(File.separator);
		} else {
			path = new StringBuilder(File.separator);
		}
		for (final String fragment : serverPath) {
			path.append(getSubPath(fragment)).append(File.separator);
		}
		for (final String fragment : fullPath) {
			path.append(getSubPath(fragment)).append(File.separator);
		}
		return path.toString();
	}

	public static File getFile(final String[] fullPath) {
		final String path = buildFullPath(fullPath);
		return new File(path);
	}

	private static String getSubPath(final String s) {
		return s.replace(File.separator, Constants.EMPTY).replace(File.pathSeparator, Constants.EMPTY);
	}

	public static byte[] readStoreFile(final String storeFilePath) throws IOException {
		final byte[] contentData;
		String[] fragmentPath;
		if (storeFilePath != null) {
			fragmentPath = storeFilePath.split(storeFilePathSeparator);
		} else {
			fragmentPath = getDefaultStreamedContentName().split(storeFilePathSeparator);
		}
		File file;
		InputStream inputStream;
		try {
			file = getFile(fragmentPath);
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			fragmentPath = getDefaultStreamedContentName().split(storeFilePathSeparator);
			file = getFile(fragmentPath);
			inputStream = new FileInputStream(file);
		}
		contentData = IOUtils.toByteArray(inputStream);
		return contentData;
	}

	public static String getDefaultStreamedContentName() {
		final String storeFilePath = buildStoreFilePath(new String[] {}, defaultFile);
		return storeFilePath;
	}

	public static String generateFileName(final String prefixFileName) {
		final StringBuilder fileName = new StringBuilder(prefixFileName);
		fileName.append("-");
		fileName.append(UUID.randomUUID().toString());
		return fileName.toString();
	}

	public static String buildStoreFilePath(final String[] path, String fileName) {
		final StringBuilder storeFilePath = new StringBuilder();
		for (final String pathFragment : path) {
			storeFilePath.append(pathFragment).append(storeFilePathSeparator);
		}
		storeFilePath.append(fileName);
		return storeFilePath.toString();
	}

	public static void writeDataToDisk(final String storeFilePath, final byte[] fileData) throws IOException {
		final String[] path = storeFilePath.split(storeFilePathSeparator);
		final String fullPath = buildFullPath(path);
		final File file = new File(fullPath);

		try (final ByteArrayInputStream inputStream = new ByteArrayInputStream(fileData);
			 final FileOutputStream outputStream = new FileOutputStream(file)) {
			IOUtils.copy(inputStream, outputStream);
		}
	}
}
