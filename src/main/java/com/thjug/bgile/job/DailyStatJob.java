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
package com.thjug.bgile.job;

import com.thjug.bgile.facade.DailyStatFacade;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author nuboat
 */
@Singleton
public class DailyStatJob implements Job {

	@Inject
	private DailyStatFacade facade;

	@Override
	public void execute(final JobExecutionContext jec) throws JobExecutionException {
		facade.execute();
	}

}
