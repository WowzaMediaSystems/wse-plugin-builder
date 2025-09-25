/*
 * This code and all components (c) Copyright 2006 - 2025, Wowza Media Systems, LLC.  All rights reserved.
 * This code is licensed pursuant to the Wowza Public License version 1.0, available at www.wowza.com/legal.
 */
package com.wowza.wms.plugin.myFirstPlugin;

import com.wowza.wms.application.IApplicationInstance;
import com.wowza.wms.application.WMSProperties;
import com.wowza.wms.logging.WMSLogger;
import com.wowza.wms.logging.WMSLoggerFactory;
import com.wowza.wms.module.ModuleBase;
import com.wowza.wms.stream.IMediaStream;
import com.wowza.wms.stream.IMediaStreamActionNotify3;
import com.wowza.wms.media.model.MediaCodecInfoAudio;
import com.wowza.wms.media.model.MediaCodecInfoVideo;
import com.wowza.wms.amf.AMFPacket;

public class MyFirstModule extends ModuleBase
{
    public static Class CLASS = MyFirstModule.class;
    public static String MODULE_NAME = CLASS.getSimpleName();
    public static final String MODULE_VERSION = ReleaseInfo.getVersion();
    private IApplicationInstance appInstance;

    protected WMSLogger logger;

    public void onAppCreate(IApplicationInstance appInstance)
    {
        logger = WMSLoggerFactory.getLoggerObj(CLASS, appInstance);
        logger.info("********************************************************");
        logger.info("* Welcome to my first Application Module Plugin v"+MODULE_VERSION+" *");
        logger.info("********************************************************");
        this.appInstance=appInstance;
    }

    public void onAppStop(IApplicationInstance appInstance)
    {
    }    

    public void onStreamCreate(IMediaStream stream)
    {

        StreamListener actionNotify = null;
        WMSProperties props = stream.getProperties();
        synchronized(props)
        {
            actionNotify = (StreamListener)stream.getProperties().get("MyFirstModule");
            if(actionNotify == null)
            {
                actionNotify = new StreamListener(this.appInstance);
            }
            props.put("MyFirstModule", actionNotify);
        }
        stream.addClientListener(actionNotify);
    }

    public void onStreamDestroy(IMediaStream stream)
    {
        StreamListener actionNotify = null;
        WMSProperties props = stream.getProperties();

        synchronized(props)
        {
            actionNotify = (StreamListener)stream.getProperties().get("MyFirstModule");
        }
        if (actionNotify != null)
        {
            stream.removeClientListener(actionNotify);
        }
    }

    class StreamListener implements IMediaStreamActionNotify3
    {
        IApplicationInstance appInstance;

        public StreamListener(IApplicationInstance appInstance)
        {
            this.appInstance = appInstance;
        }

        public void onMetaData(IMediaStream stream, AMFPacket metaDataPacket)
        {
        }

        public void onPauseRaw(IMediaStream stream, boolean isPause, double location)
        {
        }

        public void onPause(IMediaStream stream, boolean isPause, double location)
        {
        }

        public void onSeek(IMediaStream iMediaStream, double v)
        {
        }

        public void onStop(IMediaStream iMediaStream)
        {
        }

        public void onPlay(IMediaStream stream, String streamName, double playStart, double playLen, int playReset)
        {
        }

        public void onPublish(IMediaStream stream, String streamName, boolean isRecord, boolean isAppend)
        {
        }

        public void onUnPublish(IMediaStream stream, String streamName, boolean isRecord, boolean isAppend)
        {
        }

        public void onCodecInfoVideo(IMediaStream stream, MediaCodecInfoVideo mediaCodecInfoVideo)
        {
        }

        public void onCodecInfoAudio(IMediaStream iMediaStream, MediaCodecInfoAudio mediaCodecInfoAudio)
        {
        }
    }

}
