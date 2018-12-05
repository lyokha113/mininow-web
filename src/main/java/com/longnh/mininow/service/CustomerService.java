package com.longnh.mininow.service;

import com.longnh.mininow.model.Customer;

public interface CustomerService {

    public Customer getCustomer(long id);

    public Customer updateCustomer(Customer customer);

    public Customer getCustomerByUID(String uid);
}
