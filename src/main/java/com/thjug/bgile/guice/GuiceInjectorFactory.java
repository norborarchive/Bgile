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
package com.thjug.bgile.guice;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 *
 * @author @nuboat
 */
public class GuiceInjectorFactory {
	private static Injector injector;

	private static void initInjector() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		injector = Guice.createInjector(new GuiceServletModule(), new JpaPersistModule("bgileUnit"),
				new LoggingModule(), new ShiroAopModuleImpl(), new ShiroWebModuleImpl(servletContext));
		injector.getInstance(JPAInitializer.class);
	}

	public static Injector getInjector() {
		if (injector == null) {
			initInjector();
		}
		return injector;
	}
}
