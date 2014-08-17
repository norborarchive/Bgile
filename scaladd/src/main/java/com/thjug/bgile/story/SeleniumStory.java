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

import static java.util.Arrays.asList;
import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.failures.PendingStepStrategy;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.XML;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.LocalFrameContextView;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumContextOutput;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Test;

/**
 *
 * @author @nuboat
 */
public abstract class SeleniumStory extends ConfigurableEmbedder {

	final PendingStepStrategy pendingStepStrategy = new FailingUponPendingStep();
	final CrossReference crossReference = new CrossReference().withJsonOnly().withPendingStepStrategy(pendingStepStrategy)
			.withOutputAfterEachStory(true).excludingStoriesWithNoExecutedScenarios(true);
	final ContextView contextView = new LocalFrameContextView().sized(640, 120);
	final SeleniumContext seleniumContext = new SeleniumContext();
	final Format[] formats = new Format[]{new SeleniumContextOutput(seleniumContext), CONSOLE, WEB_DRIVER_HTML, XML};
	final SeleniumStepMonitor stepMonitor = new SeleniumStepMonitor(contextView, seleniumContext,
			crossReference.getStepMonitor());
	final StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
			.withCodeLocation(codeLocationFromClass(SeleniumStory.class)).withFailureTrace(true)
			.withFailureTraceCompression(true).withDefaultFormats().withFormats(formats)
			.withCrossReference(crossReference);

	@Override
	public Configuration configuration() {
		return new SeleniumConfiguration()
				.useSeleniumContext(seleniumContext)
				.useParameterControls(new ParameterControls().useDelimiterNamedParameters(true))
				.usePendingStepStrategy(pendingStepStrategy).useStepMonitor(stepMonitor)
				.useStoryLoader(new LoadFromClasspath(SeleniumStory.class)).useStoryReporterBuilder(reporterBuilder);
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		final ApplicationContext context = new SpringApplicationContextFactory("steps.xml")
				.createApplicationContext();
		return new SpringStepsFactory(configuration(), context);
		//return new InstanceStepsFactory(configuration(), new BgileStep(new DefaultWebDriverProvider()));
	}

	@Test
	@Override
	public void run() throws Throwable {
//		final EmbedderControls embedderControls = new EmbedderControls();
//		embedderControls.useStoryTimeoutInSecs(360000);

		final Embedder embedder = configuredEmbedder();
		//embedder.useEmbedderControls(embedderControls);
		try {
			final String storyPath = this.toString();
			embedder.runStoriesAsPaths(asList(storyPath));
		} finally {
			embedder.generateCrossReference();
		}
	}

}
