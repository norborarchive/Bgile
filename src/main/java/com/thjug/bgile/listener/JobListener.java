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
package com.thjug.bgile.listener;

import com.thjug.bgile.guice.GuiceInjectorFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author nuboat
 */
public class JobListener implements ServletContextListener {

	private static final Logger LOG = LoggerFactory.getLogger(JobListener.class);

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		LOG.info("Job Shutdown");
		try {
			GuiceInjectorFactory.getInjector().getInstance(Scheduler.class).shutdown();
		} catch (final SchedulerException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
