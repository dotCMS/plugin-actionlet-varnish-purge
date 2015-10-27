package com.dotcms.varnish;

import com.dotcms.repackage.org.osgi.framework.BundleContext;
import com.dotcms.varnish.VarnishPurgeActionlet;
import com.dotmarketing.osgi.GenericBundleActivator;

public class Activator extends GenericBundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {

		// Initializing services...
		initializeServices(bundleContext);

		// Registering the test Actionlet
		registerActionlet(bundleContext, new VarnishPurgeActionlet());
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		unregisterActionlets();
	}

}