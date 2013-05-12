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

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.thjug.bgile.interceptor.LoggingInterceptor;
import com.thjug.bgile.interceptor.Logging;

/**
*
* @author Wasan Anusornhirunkarn, @tone
*/
public class LoggingModule extends AbstractModule {

	@Override
	protected void configure() {
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(Logging.class), new LoggingInterceptor());
	}
}
