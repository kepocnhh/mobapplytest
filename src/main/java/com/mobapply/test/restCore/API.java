package com.mobapply.test.restCore;

import com.google.gson.Gson;
import com.mobapply.test.models.Order;
import com.mobapply.test.restCore.response.OrdersResponse;

import java.util.List;

import retrofit.http.GET;

public interface API
{
    //**************************** POST *******************************\\

    //**************************** PUT  *******************************\\

    //**************************** GET  *******************************\\

    // --------------------------------- ORDERS
    @GET("/orders")
    List<Order> getOrders();
}