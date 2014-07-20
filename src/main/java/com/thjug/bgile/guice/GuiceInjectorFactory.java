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

import com.thjug.bgile.module.ShiroWebModuleImpl;
import com.thjug.bgile.module.GuiceServletModule;
import com.thjug.bgile.module.LoggingModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.thjug.bgile.module.QuartzModuleImpl;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.shiro.guice.aop.ShiroAopModule;

/**
 *
 * @author @nuboat
 */
public class GuiceInjectorFactory {

	private static final Injector injector;

	static {
		final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		injector = Guice.createInjector(new GuiceServletModule(), new JpaPersistModule("bgileUnit"),
				new LoggingModule(), new ShiroAopModule(), new ShiroWebModuleImpl(servletContext),
				new QuartzModuleImpl());
		injector.getInstance(JPAInitializer.class);
	}

	public static Injector getInjector() {
		return injector;
	}

	public static <T> T getInstance(final Class<? extends T> type) {
		return injector.getInstance(type);
	}

	private GuiceInjectorFactory() {
	}

}
