package com.mobapply.test.restCore.request.get;

import com.mobapply.test.models.Order;
import com.mobapply.test.restCore.request.Request;
import com.mobapply.test.restCore.response.OrdersResponse;

import java.util.List;

public class OrdersRequest
    extends Request
{
    public OrdersRequest()
    {
        super();
    }

    @Override
    public OrdersResponse loadDataFromNetwork() throws Exception
    {
        super.loadDataFromNetwork();
        List<Order> orders = getService().getOrders();
        return new OrdersResponse(orders);
    }
}
