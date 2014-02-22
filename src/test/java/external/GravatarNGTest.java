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
package external;

import com.timgroup.jgravatar.Gravatar;
import com.timgroup.jgravatar.GravatarDefaultImage;
import com.timgroup.jgravatar.GravatarRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * 
 * @author nuboat
 */
public class GravatarNGTest {

	private static final Logger LOG = LoggerFactory.getLogger(GravatarNGTest.class);

	@Test
	public void testGetGravatarUrl() {
		final Gravatar gravatar = new Gravatar();
		gravatar.setSize(32);
		gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
		gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);

		final String url = gravatar.getUrl("nuboat@gmail.com");
		LOG.debug("Gravatar" + url);

	}
}
