package com.mobapply.test.spice;

import com.mobapply.test.Settings;
import com.mobapply.test.restCore.API;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

public class SpiceService
        extends RetrofitGsonSpiceService
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        addRetrofitInterface(API.class);
    }

    @Override
    protected String getServerUrl()
    {
        return Settings.getBase_url();
    }

    @Override
    public int getThreadCount()
    {
        return 4;
    }
}