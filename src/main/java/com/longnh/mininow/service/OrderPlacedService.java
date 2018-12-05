package com.longnh.mininow.service;

import com.longnh.mininow.model.OrderItem;
import com.longnh.mininow.model.OrderPlaced;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface OrderPlacedService {

    public OrderPlaced createOrder(Map<String, Object> request) throws IOException;

    public OrderPlaced getOrder(long id);

    public List<OrderPlaced> getOngoingOrder(int status, long id);

    public List<OrderPlaced> getFinishedOrder(int status, long id);

    public void changeOrderStatus(int status, long id);

}
