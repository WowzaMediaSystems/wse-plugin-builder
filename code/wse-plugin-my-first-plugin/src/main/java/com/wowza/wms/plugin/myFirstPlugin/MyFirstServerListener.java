package com.wowza.wms.plugin.myFirstPlugin;

import com.wowza.wms.logging.*;
import com.wowza.wms.server.*;

public class MyFirstServerListener implements IServerNotify2
{
    public static Class CLASS = MyFirstServerListener.class;
    public static String MODULE_NAME = CLASS.getSimpleName();
    public static final String MODULE_VERSION = ReleaseInfo.getVersion();
    protected WMSLogger logger;

	/*
	 * Called after the server is created but not initialized.
	 */
	@Override
	public void onServerCreate(IServer server)
	{
		WMSLogger log = WMSLoggerFactory.getLogger(null);
	}

	/*
	 * Called after the server is initialized. At this point the server is running, all virtual hosts are initialized, and the server is ready to accept connections.
	 */
	@Override
	public void onServerInit(IServer server)
	{
		logger = WMSLoggerFactory.getLogger(null);
		logger.info("**********************************************");
        logger.info("* Welcome to my first Server Listener v"+MODULE_VERSION+" *");
		logger.info("**********************************************");
	}

	/*
	 * Called when the server starts to shut down.
	 */
	@Override
	public void onServerShutdownStart(IServer server)
	{
		WMSLoggerFactory.getLogger(null).info("onServerShutdownStart");
	}

	/*
	 * Called when the server has finished shutting down. This is the last event that is triggered before the Wowza Streaming Engine process exits.
	 */
	@Override
	public void onServerShutdownComplete(IServer server)
	{
		WMSLoggerFactory.getLogger(null).info("onServerShutdownComplete");
	}

	/*
	 * Called after the Server.xml file has been loaded. This is the earliest point where you can access server events.
	 */
	@Override
	public void onServerConfigLoaded(IServer server)
	{
		WMSLoggerFactory.getLogger(null).info("onServerConfigLoaded");
	}

}
