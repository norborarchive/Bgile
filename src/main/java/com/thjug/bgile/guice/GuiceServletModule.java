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
package com.thjug.bgile.guice;

import java.util.HashMap;
import java.util.Map;

import com.thjug.bgile.servlet.EncodingFilter;
import com.thjug.bgile.servlet.GuiceFileUploadFilter;

import com.google.inject.servlet.ServletModule;
import org.apache.shiro.guice.web.GuiceShiroFilter;

/**
*
* @author Wasan Anusornhirunkarn, @tone
*/
public class GuiceServletModule extends ServletModule {
	@Override
	protected void configureServlets() {
		Map<String, String> encodingFilterInitParams = new HashMap<>();
		encodingFilterInitParams.put("encoding", "UTF-8");
		//filter("/*").through(EncodingFilter.class, encodingFilterInitParams);
		//filter("/*").through(GuiceShiroFilter.class);
		//filter("*.xhtml").through(GuiceFileUploadFilter.class);
	}
}
