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
package com.thjug.bgile.managed;

import com.timgroup.jgravatar.Gravatar;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * references. https://en.gravatar.com/site/implement/images/
 *
 * @author nuboat
 */
@SessionScoped
@ManagedBean(name = "gravatar")
public class GravatarManaged extends AccountAbstractManaged {

	private final transient Gravatar gravatar = new Gravatar();
	private final transient Random random = new Random();

	private static final String[] DEFAULT_GRAVATAR = {"mm", "identicon", "monsterid", "wavatar", "retro"};

	public String getGravatarUrl(final String email) {
		Object gravatarUrl = getSession().getAttribute(genSessionKey(email));
		if (gravatarUrl == null) {
			gravatarUrl = gravatar.getUrl(email).replace("404", getRandomGravatar());
			getSession().setAttribute(genSessionKey(email), gravatarUrl);
		}

		return gravatarUrl.toString();
	}

	private static String genSessionKey(final String email) {
		return "GRAVATARURL_" + email;
	}

	private String getRandomGravatar() {
		return DEFAULT_GRAVATAR[getRandomIndex(DEFAULT_GRAVATAR.length - 1)];
	}

	private int getRandomIndex(final int max) {
		return random.nextInt(max - 0) + 0;
	}

}
