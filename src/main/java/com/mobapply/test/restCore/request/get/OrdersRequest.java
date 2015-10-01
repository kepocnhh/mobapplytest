package com.mobapply.test.restCore.request.get;

import com.mobapply.test.restCore.request.Request;
import com.mobapply.test.restCore.response.OrdersResponse;

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
        OrdersResponse response = getService().getOrders();
        return response;
    }
}
