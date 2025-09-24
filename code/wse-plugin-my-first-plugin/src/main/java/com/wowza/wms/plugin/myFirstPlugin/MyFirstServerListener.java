package com.wowza.wms.plugin.myFirstPlugin;

import com.wowza.wms.logging.*;
import com.wowza.wms.server.*;

public class MyFirstServerListener implements IServerNotify2
{
    public static Class CLASS = MyFirstServerListener.class;
    public static String MODULE_NAME = CLASS.getSimpleName();
    public static final String MODULE_VERSION = ReleaseInfo.getVersion();
    protected WMSLogger logger;

	@Override
	public void onServerCreate(IServer server)
	{
		WMSLogger log = WMSLoggerFactory.getLogger(null);
	}

	@Override
	public void onServerInit(IServer server)
	{
		logger = WMSLoggerFactory.getLogger(null);
		logger.info("**********************************************");
        logger.info("* Welcome to my first Server Listener v"+MODULE_VERSION+" *");
		logger.info("**********************************************");
	}

	@Override
	public void onServerShutdownStart(IServer server)
	{
	}

	@Override
	public void onServerShutdownComplete(IServer server)
	{
	}

	@Override
	public void onServerConfigLoaded(IServer server)
	{
	}

}
