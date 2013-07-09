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
package com.thjug.bgile.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
public final class LoggingInterceptor implements MethodInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);

	@Override
	public Object invoke(final MethodInvocation invocation) throws Throwable {
		final Long start = System.currentTimeMillis();
		final String className = invocation.getMethod().getDeclaringClass().getSimpleName();
		final String methodName = invocation.getMethod().getName();

		try {
			return invocation.proceed();
		} finally {
			final Long finish = System.currentTimeMillis();
			final Long usageTime = finish - start;
			LOG.info("Run {} of Class {} in {} ms.", new Object[] { methodName, className, usageTime });
		}
	}

}
