/**
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
package com.thjug.bgile.module;

import com.thjug.bgile.job.DailyStatJob;
import org.apache.onami.scheduler.QuartzModule;

/**
 *
 * @author nuboat
 */
public class QuartzModuleImpl extends QuartzModule {

	@Override
	protected void schedule() {
		scheduleJob(DailyStatJob.class).withCronExpression("0 1 0 * * ?").withJobName(
				DailyStatJob.class.getSimpleName());
	}

}
