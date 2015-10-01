package com.mobapply.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mobapply.test.restCore.API;
import com.mobapply.test.restCore.request.get.OrdersRequest;
import com.mobapply.test.restCore.response.OrdersResponse;
import com.mobapply.test.restCore.response.Response;
import com.mobapply.test.spice.SpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;


public class Main
        extends ActionBarActivity
{
    SpiceManager spiceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //
        Settings.setBase_url(getResources().getString(R.string.base_url));
        //
        init();
    }

    private void init3()
    {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Settings.getBase_url())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient()))
                .build();
        API api = restAdapter.create(API.class);
        OrdersResponse ordersResponse = api.getOrders();
        int i = ordersResponse.orderList.size();
    }
    private void init()
    {
        //
        spiceManager = new SpiceManager(SpiceService.class);
        OrdersRequest request = new OrdersRequest();
        spiceManager.execute(request, new RequestListener<Response>()
        {
            @Override
            public void onRequestFailure(SpiceException spiceException)
            {
                Toast.makeText(getApplicationContext(), spiceException.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestSuccess(Response response)
            {
                OrdersResponse ordersResponse = (OrdersResponse)response;
                int i = ordersResponse.orderList.size();
            }
        });
    }
}