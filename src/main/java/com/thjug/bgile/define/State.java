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
package com.thjug.bgile.define;

/**
 *
 * @author @nuboat
 */
public enum State {

	Plan(0),
	Process(1),
	Test(2),
	Done(3),
	Archive(4);

	private final int id;

	private State(final int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
