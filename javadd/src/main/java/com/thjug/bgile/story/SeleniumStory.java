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
package com.thjug.bgile.story;

import com.google.common.util.concurrent.MoreExecutors;
import com.thjug.bgile.step.PageStep;
import com.thjug.bgile.webdriver.DefaultWebDriverProvider;
import static java.util.Arrays.asList;
import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import org.jbehave.core.io.LoadFromClasspath;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.LocalFrameContextView;
import org.jbehave.web.selenium.PerStoriesWebDriverSteps;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverSteps;
import org.testng.annotations.Test;

/**
 *
 * @author @nuboat
 */
public abstract class SeleniumStory extends ConfigurableEmbedder {

	private final WebDriverProvider driverProvider = new DefaultWebDriverProvider();
	private final WebDriverSteps lifecycleSteps = new PerStoriesWebDriverSteps(driverProvider); // or PerStoryWebDriverSteps(driverProvider)
	private final SeleniumContext context = new SeleniumContext();
	private final ContextView contextView = new LocalFrameContextView().sized(500, 100);

	public SeleniumStory() {
		if (lifecycleSteps instanceof PerStoriesWebDriverSteps) {
			configuredEmbedder().useExecutorService(MoreExecutors.sameThreadExecutor());
		}
	}

	public abstract String getStory();

	@Override
	public Configuration configuration() {
		Class<? extends Embeddable> embeddableClass = this.getClass();
		return new SeleniumConfiguration()
				.useSeleniumContext(context)
				.useWebDriverProvider(driverProvider)
				.useStepMonitor(new SeleniumStepMonitor(contextView, context, new SilentStepMonitor()))
				.useStoryLoader(new LoadFromClasspath(embeddableClass))
				.useStoryReporterBuilder(new StoryReporterBuilder()
						.withCodeLocation(codeLocationFromClass(embeddableClass))
						.withDefaultFormats()
						.withFormats(CONSOLE, TXT, HTML, XML));
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		Configuration configuration = configuration();
		return new InstanceStepsFactory(configuration,
				new PageStep(driverProvider),
				lifecycleSteps);
	}

	@Test
	@Override
	public void run() throws Throwable {
//		final EmbedderControls embedderControls = new EmbedderControls();
//		embedderControls.useStoryTimeoutInSecs(360000);

		final Embedder embedder = configuredEmbedder();
		//embedder.useEmbedderControls(embedderControls);
		try {
			embedder.runStoriesAsPaths(asList(getStory()));
		} finally {
			embedder.generateCrossReference();
		}
	}

	public static class SameThreadEmbedder extends Embedder {

		public SameThreadEmbedder() {
			useExecutorService(MoreExecutors.sameThreadExecutor());
		}
	}
}
