package com.longnh.mininow.repository;

import com.longnh.mininow.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
