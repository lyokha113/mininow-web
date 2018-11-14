package com.longnh.mininow.service;

import com.longnh.mininow.model.Customer;

public interface CustomerService {

    public Customer getCustomer(long id);

    public Customer changeAvatar(long id, String url);

    public Customer changeInfo(Customer customer);
}
