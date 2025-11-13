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
import com.wowza.wms.stream.*;
import com.wowza.wms.media.model.MediaCodecInfoAudio;
import com.wowza.wms.media.model.MediaCodecInfoVideo;
import com.wowza.wms.amf.AMFPacket;

public class MyFirstModule extends ModuleBase
{
    public static Class CLASS = MyFirstModule.class;
    public static String MODULE_NAME = CLASS.getSimpleName();
    public static final String MODULE_VERSION = ReleaseInfo.getVersion();
    private IApplicationInstance appInstance;
    private IMediaStreamNotify mediaStreamListener = new MediaStreamListener();

    protected WMSLogger logger;

    public void onAppCreate(IApplicationInstance appInstance)
    {
        logger = WMSLoggerFactory.getLoggerObj(CLASS, appInstance);
        logger.info("********************************************************");
        logger.info("* Welcome to my first Application Module Plugin v"+MODULE_VERSION+" *");
        logger.info("********************************************************");
        this.appInstance=appInstance;
    }

    public void onAppStart(IApplicationInstance appInstance)
    {
        WMSLoggerFactory.getLogger(null).info("onAppStart");
        appInstance.addMediaStreamListener(mediaStreamListener);
    }

    public void onAppStop(IApplicationInstance appInstance)
    {
        WMSLoggerFactory.getLogger(null).info("onAppStop");
        appInstance.removeMediaStreamListener(mediaStreamListener);
    }

    public void onStreamCreate(IMediaStream stream)
    {
        WMSLoggerFactory.getLogger(null).info("onStreamCreate: " + stream.getSrc());
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
        WMSLoggerFactory.getLogger(null).info("onStreamDestroy: " + stream.getSrc());
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
            WMSLoggerFactory.getLogger(null).info("onMetaData: " + stream.getName());
        }

        public void onPauseRaw(IMediaStream stream, boolean isPause, double location)
        {
            WMSLoggerFactory.getLogger(null).info("onPauseRaw: " + stream.getName());
        }

        public void onPause(IMediaStream stream, boolean isPause, double location)
        {
            WMSLoggerFactory.getLogger(null).info("onPause: " + stream.getName());
        }

        public void onSeek(IMediaStream stream, double v)
        {
            WMSLoggerFactory.getLogger(null).info("onSeek: " + stream.getName());
        }

        public void onStop(IMediaStream stream)
        {
            WMSLoggerFactory.getLogger(null).info("onStop: " + stream.getName());
        }

        public void onPlay(IMediaStream stream, String streamName, double playStart, double playLen, int playReset)
        {
            WMSLoggerFactory.getLogger(null).info("onPlay: " + stream.getName());
        }

        public void onPublish(IMediaStream stream, String streamName, boolean isRecord, boolean isAppend)
        {
            WMSLoggerFactory.getLogger(null).info("onPublish: " + stream.getName());
        }

        public void onUnPublish(IMediaStream stream, String streamName, boolean isRecord, boolean isAppend)
        {
            WMSLoggerFactory.getLogger(null).info("onUnPublish: " + stream.getName());
        }

        public void onCodecInfoVideo(IMediaStream stream, MediaCodecInfoVideo mediaCodecInfoVideo)
        {
            WMSLoggerFactory.getLogger(null).info("onCodecInfoVideo: " + stream.getName());
        }

        public void onCodecInfoAudio(IMediaStream stream, MediaCodecInfoAudio mediaCodecInfoAudio)
        {
            WMSLoggerFactory.getLogger(null).info("onCodecInfoAudio: " + stream.getName());
        }
    }


    public class MediaStreamListener implements IMediaStreamNotify
    {

        @Override
        public void onMediaStreamCreate(IMediaStream stream)
        {
            WMSLoggerFactory.getLogger(null).info("onMediaStreamCreate: " + stream.getSrc());
        }

        @Override
        public void onMediaStreamDestroy(IMediaStream stream)
        {
            WMSLoggerFactory.getLogger(null).info("onMediaStreamDestroy: " + stream.getSrc());
        }
    } 

}
