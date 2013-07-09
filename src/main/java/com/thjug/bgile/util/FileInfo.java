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

import org.primefaces.model.UploadedFile;

/**
 *
 * @author @nuboat
 */
public final class FileInfo {
	private String name;
	private String size;
	private String contentType;

	public FileInfo() {

	}

	public FileInfo(final UploadedFile uploadedFile) {
		name = uploadedFile.getFileName();
		size = StringUtility.readableFileSize(uploadedFile.getSize());
		contentType = uploadedFile.getContentType();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(final String size) {
		this.size = size;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(final String contentType) {
		this.contentType = contentType;
	}

}
