/*
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
package com.thjug.bgile.managed;

import com.sun.faces.application.resource.ResourceHandlerImpl;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
//@SessionScoped
//@ManagedBean(name = "streamed")
public class StreamedContentManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(StreamedContentManaged.class);
	private static final String DEFAULT_RESOURCE = "images/default.png";

	private Map<String, byte[]> contentDataMap;

	public StreamedContentManaged() {
		contentDataMap = new HashMap<>();
	}

	public StreamedContent getContent(final String streamedContentId) {

		final StreamedContent content;
		final byte[] contentData = contentDataMap.get(streamedContentId);
		if (contentData != null) {
			content = new DefaultStreamedContent(new ByteArrayInputStream(contentData));
			return content;
		}
		return null;
	}

	public byte[] getContentData(final String streamedContentId) {

		return contentDataMap.get(streamedContentId);
	}

	public void putStreamedContent(final UploadedFile uploadedFile, final String streamedContentId) throws IOException {

		final byte[] contentData = IOUtils.toByteArray(uploadedFile.getInputstream());
		contentDataMap.put(streamedContentId, contentData);
	}

	public StreamedContent getStreamedContent() throws IOException {

		final Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		final String streamedContentId = params.get("streamedContentId");
		final StreamedContent content = getContent(streamedContentId);
		if (content != null) {
			return content;
		}
		final ResourceHandler resourceHandler = new ResourceHandlerImpl();
		final Resource resource = resourceHandler.createResource(DEFAULT_RESOURCE, "default");
		try {
			return new DefaultStreamedContent(resource.getInputStream());
		} catch (final IOException e) {
			LOG.warn(e.getMessage());
			throw e;
		}

	}

	public void clear() {
		contentDataMap.clear();
	}
}
