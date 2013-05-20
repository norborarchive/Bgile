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
package com.thjug.bgile.managed;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.faces.application.resource.ResourceHandlerImpl;

/**
*
* @author Wasan Anusornhirunkarn, @tone
*/
@ManagedBean
@SessionScoped
public class StreamedContentManaged extends AbstractManaged {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(StreamedContentManaged.class);
	private static final String defaultResource = "images/default.png";

	private Map<String, byte[]> contentDataMap;

	public StreamedContentManaged() {
		contentDataMap = new HashMap<>();
	}

	public StreamedContent getContent(final String streamedContentId) {
		StreamedContent content = null;
		final byte[] contentData = contentDataMap.get(streamedContentId);
		if (contentData != null) {
			content = new DefaultStreamedContent(new ByteArrayInputStream(contentData));
		}
		return content;
	}

	public byte[] getContentData(final String streamedContentId) {
		return contentDataMap.get(streamedContentId);
	}

	public void putStreamedContent(final UploadedFile uploadedFile, final String streamedContentId) throws IOException {
		final byte[] contentData = IOUtils.toByteArray(uploadedFile.getInputstream());
		contentDataMap.put(streamedContentId, contentData);
	}

	public StreamedContent getStreamedContent() {
		final Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		final String streamedContentId = params.get("streamedContentId");
		StreamedContent content = getContent(streamedContentId);
		if (content == null) {
			final ResourceHandler resourceHandler = new ResourceHandlerImpl();
			final Resource resource = resourceHandler.createResource(defaultResource, "default");
			try {
				content = new DefaultStreamedContent(resource.getInputStream());
			} catch (final IOException e) {
				LOG.warn(e.getMessage());
			}
		}
		return content;
	}

	public void clear() {
		contentDataMap.clear();
	}
}
