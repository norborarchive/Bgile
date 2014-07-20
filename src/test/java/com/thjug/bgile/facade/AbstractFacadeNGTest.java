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
package com.thjug.bgile.facade;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.thjug.bgile.guice.JPAInitializer;
import com.thjug.bgile.module.LoggingModule;

/**
 *
 * @author @nuboat
 */
public abstract class AbstractFacadeNGTest {

	protected final Injector injector;

	public AbstractFacadeNGTest() {
		injector = Guice.createInjector(new LoggingModule(), new JpaPersistModule("bgileUnit"));
		injector.getInstance(JPAInitializer.class);
	}

}
