/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package external;

import com.timgroup.jgravatar.Gravatar;
import com.timgroup.jgravatar.GravatarDefaultImage;
import com.timgroup.jgravatar.GravatarRating;
import org.testng.annotations.Test;

/**
 *
 * @author nuboat
 */
public class GravatarNGTest {

	@Test
	public void testGetGravatarUrl() {
		final Gravatar gravatar = new Gravatar();
		gravatar.setSize(32);
		gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
		gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);

		final String url = gravatar.getUrl("nuboat@gmail.com");
		System.out.println(url);
	}
}
