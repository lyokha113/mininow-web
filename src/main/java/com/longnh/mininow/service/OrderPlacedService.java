package com.longnh.mininow.service;

import com.longnh.mininow.model.OrderPlaced;

import java.util.List;

public interface OrderPlacedService {

    public OrderPlaced createOrder(OrderPlaced order);

    public OrderPlaced getOrder(long id);

    public List<OrderPlaced> getOngoingOrder(int status, long id);

    public List<OrderPlaced> getFinishedOrder(int status, long id);

    public void changeOrderStatus(int status, long id);

}
