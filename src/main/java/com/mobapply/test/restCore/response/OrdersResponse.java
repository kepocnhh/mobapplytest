package com.mobapply.test.restCore.response;

import com.mobapply.test.models.Order;

import java.util.List;

public class OrdersResponse
    extends Response
{
    public List<Order> orderList;

    public OrdersResponse(List<Order> ol)
    {
        orderList = ol;
    }
}
