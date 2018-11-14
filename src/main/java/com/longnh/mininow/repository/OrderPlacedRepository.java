package com.longnh.mininow.repository;

import com.longnh.mininow.model.OrderPlaced;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderPlacedRepository extends CrudRepository<OrderPlaced, Long> {

    public List<OrderPlaced> findByStatusLessThanAndCustomer_IdEquals(int status, long customerId);
    public List<OrderPlaced> findByStatusGreaterThanAndCustomer_IdEquals(int status, long customerId);
}
