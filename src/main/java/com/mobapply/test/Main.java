package com.mobapply.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mobapply.test.fragments.MapFragment;
import com.mobapply.test.models.Order;
import com.mobapply.test.restCore.request.get.OrdersRequest;
import com.mobapply.test.restCore.response.OrdersResponse;
import com.mobapply.test.restCore.response.Response;
import com.mobapply.test.spice.SpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

public class Main
        extends ActionBarActivity
{
    private SpiceManager spiceManager;

    private MapFragment mapWindow;

    private ProgressBar waiter;

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

    private void init()
    {
        waiter = (ProgressBar)findViewById(R.id.waiter);
        waiter.setVisibility(View.VISIBLE);
        request();
    }

    private void request()
    {
        spiceManager = new SpiceManager(SpiceService.class);
        spiceManager.start(this);
        OrdersRequest request = new OrdersRequest();
        spiceManager.execute(request, new RequestListener<Response>()
        {
            @Override
            public void onRequestFailure(SpiceException spiceException)
            {
                Toast.makeText(getApplicationContext(), spiceException.getMessage(),
                        Toast.LENGTH_SHORT).show();
                waiter.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onRequestSuccess(Response response)
            {
                OrdersResponse ordersResponse = (OrdersResponse) response;
                requestSuccess(ordersResponse.orderList);
            }
        });
    }

    private void requestSuccess(List<Order> orderList)
    {
        waiter.setVisibility(View.INVISIBLE);
        mapWindow = MapFragment.getInstance(orderList);
        getSupportFragmentManager().beginTransaction().
                add(R.id.mapFrame, mapWindow).
                addToBackStack("MapWindowFragment").
                commit();
        spiceManager.shouldStop();
    }
}