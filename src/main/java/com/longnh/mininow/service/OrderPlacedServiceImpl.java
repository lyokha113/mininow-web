package com.longnh.mininow.service;

import com.longnh.mininow.model.OrderPlaced;
import com.longnh.mininow.repository.OrderPlacedRepository;
import com.longnh.mininow.util.ConstainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderPlacedServiceImpl implements OrderPlacedService {

    @Autowired
    OrderPlacedRepository orderPlacedRepository;

    @Override
    public OrderPlaced createOrder(OrderPlaced order) {
        return orderPlacedRepository.save(order);
    }

    @Override
    public OrderPlaced getOrder(long id) {
        Optional<OrderPlaced> order = orderPlacedRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public List<OrderPlaced> getOngoingOrder(int status, long id) {
        return orderPlacedRepository.findByStatusLessThanAndCustomer_IdEquals(status, id);
    }

    @Override
    public List<OrderPlaced> getFinishedOrder(int status, long id) {
        return orderPlacedRepository.findByStatusGreaterThanAndCustomer_IdEquals(status, id);
    }

    @Override
    public void changeOrderStatus(int status, long id) {
        Optional<OrderPlaced> order = orderPlacedRepository.findById(id);
        if (order.isPresent()) {
            OrderPlaced orderPlaced = order.get();
            orderPlaced.setStatus(status);
            orderPlacedRepository.save(orderPlaced);
        }
    }
}
