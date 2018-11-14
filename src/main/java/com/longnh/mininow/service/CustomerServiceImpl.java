package com.longnh.mininow.service;

import com.longnh.mininow.model.Customer;
import com.longnh.mininow.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public Customer changeAvatar(long id, String url) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customer.get().setImgURL(url);
            return customerRepository.save(customer.get());
        } else return null;
    }

    @Override
    public Customer changeInfo(Customer customer) {
        Optional<Customer> result = customerRepository.findById(customer.getId());
        if (result.isPresent()) {
            Customer c = result.get();
            customer.setImgURL(c.getImgURL());
            return customerRepository.save(customer);
        } else return null;
    }
}
