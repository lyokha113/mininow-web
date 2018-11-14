package com.longnh.mininow.controller;


import com.longnh.mininow.model.Customer;
import com.longnh.mininow.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity getCustomer(@PathVariable("id") long id) {
        Customer customer = customerService.getCustomer(id);
        if (customer == null ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @RequestMapping(value = "/customer/", method = RequestMethod.PUT)
    public ResponseEntity updateInfo(@RequestBody Customer customer) {
        Customer result = customerService.changeInfo(customer);
        if (result == null ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
