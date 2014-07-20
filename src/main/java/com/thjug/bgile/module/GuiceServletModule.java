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
package com.thjug.bgile.module;

import com.google.inject.servlet.ServletModule;
import com.thjug.bgile.servlet.EncodingFilter;
import com.thjug.bgile.servlet.GuiceFileUploadFilter;
import com.thjug.bgile.servlet.RememberFilter;
import com.thjug.bgile.servlet.SigninFilter;
import com.thjug.bgile.servlet.URLFilter;
import org.apache.shiro.guice.web.GuiceShiroFilter;

/**
 * 
 * @author @nuboat
 */
public class GuiceServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		filter("/*").through(GuiceShiroFilter.class);
		filter("/*").through(GuiceFileUploadFilter.class);
		filter("/*").through(SigninFilter.class);
		filter("/*").through(EncodingFilter.class);
		filter("/*").through(RememberFilter.class);
		filter("/*").through(URLFilter.class);
	}
}
