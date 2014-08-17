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
import java.util.Arrays.asList;
import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.Format.CONSOLE;
import org.jbehave.core.reporters.Format.HTML;
import org.jbehave.core.reporters.Format.TXT;
import org.jbehave.core.reporters.Format.XML;
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
abstract class SeleniumStory extends ConfigurableEmbedder {

  val driverProvider = new DefaultWebDriverProvider()
  val lifecycleSteps = new PerStoriesWebDriverSteps(driverProvider)
  val context = new SeleniumContext()
  val contextView = new LocalFrameContextView().sized(500, 100)

  override def configuration():Configuration = {
    return new SeleniumConfiguration()
    .useSeleniumContext(context)
    .useWebDriverProvider(driverProvider)
    .useStepMonitor(new SeleniumStepMonitor(contextView, context, new SilentStepMonitor()))
    .useStoryLoader(new LoadFromClasspath(this.getClass()))
    .useStoryReporterBuilder(new StoryReporterBuilder()
                             .withCodeLocation(codeLocationFromClass(this.getClass()))
                             .withDefaultFormats()
                             .withFormats(CONSOLE, TXT, HTML, XML));
  }

  override def stepsFactory():InjectableStepsFactory = {
    return new InstanceStepsFactory(configuration,
                                    new PageStep(driverProvider),
                                    lifecycleSteps);
  }

  @Test
  override def run() {
    if (lifecycleSteps.isInstanceOf[PerStoriesWebDriverSteps]) {
      configuredEmbedder().useExecutorService(MoreExecutors.sameThreadExecutor());
    }

    val embedder = configuredEmbedder();
    embedder.useExecutorService(MoreExecutors.sameThreadExecutor());
    try {
      embedder.runStoriesAsPaths(asList(this.toString()));
    } finally {
      embedder.generateCrossReference();
    }
  }

}
