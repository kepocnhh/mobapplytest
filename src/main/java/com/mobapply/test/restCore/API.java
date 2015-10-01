package com.mobapply.test.restCore;

import com.mobapply.test.restCore.response.OrdersResponse;

import retrofit.http.GET;

public interface API
{
    //**************************** POST *******************************\\

    //**************************** PUT  *******************************\\

    //**************************** GET  *******************************\\

    // --------------------------------- ORDERS
    @GET("/orders")
    OrdersResponse getOrders();
}