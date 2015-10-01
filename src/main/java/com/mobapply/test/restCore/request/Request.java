package com.mobapply.test.restCore.request;

import android.util.Log;

import com.mobapply.test.restCore.API;
import com.mobapply.test.restCore.response.Response;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

public class Request
        extends RetrofitSpiceRequest<Response, API>
{

    public Request()
    {
        super(Response.class, API.class);
    }

    @Override
    public Response loadDataFromNetwork() throws Exception
    {
        Log.e("Exec", "****************************************Request*************************************************");
        return null;
    }
}
