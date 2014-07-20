package com.thjug.bgile.guice;

import com.google.inject.Injector;
import com.sun.faces.spi.InjectionProvider;
import com.sun.faces.spi.InjectionProviderException;
import com.sun.faces.vendor.WebContainerInjectionProvider;

public class GuiceInjectionProvider implements InjectionProvider {

	/**
	 * default injector provided by the web container.
	 */
	private static final WebContainerInjectionProvider CON = new WebContainerInjectionProvider();

	/**
	 * Custom guice injector that will load our modules.
	 */
	private static final Injector injector = GuiceInjectorFactory.getInjector();

	@Override
	public void inject(final Object managedBean) throws InjectionProviderException {
		// allow the default injector to inject the bean.
		CON.inject(managedBean);
		// then inject with the google injector.
		injector.injectMembers(managedBean);
	}

	@Override
	public void invokePostConstruct(final Object managedBean) throws InjectionProviderException {
		// don't do anything here for guice, just let the default do its thing
		CON.invokePostConstruct(managedBean);
	}

	@Override
	public void invokePreDestroy(final Object managedBean) throws InjectionProviderException {
		// don't do anything here for guice, just let the default do its thing
		CON.invokePreDestroy(managedBean);
	}

}
