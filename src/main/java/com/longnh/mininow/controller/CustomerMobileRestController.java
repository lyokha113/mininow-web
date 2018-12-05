package com.longnh.mininow.controller;


import com.longnh.mininow.model.Customer;
import com.longnh.mininow.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mobile/api")
public class CustomerMobileRestController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity getCustomer(@PathVariable("id") long id) {
        Customer customer = customerService.getCustomer(id);
        if (customer == null ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @RequestMapping(value = "/customer/uid/{uid}", method = RequestMethod.GET)
    public ResponseEntity getCustomerByUID(@PathVariable("uid") String uid) {
        Customer customer = customerService.getCustomerByUID(uid);
        if (customer == null ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @RequestMapping(value = "/customer/", method = RequestMethod.PUT)
    public ResponseEntity updateInfo(@RequestBody Customer customer) {
        Customer existCustomer = customerService.getCustomer(customer.getId());
        if(existCustomer == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer isn't existed");
        if(!StringUtils.isEmpty(customer.getImgURL()))
            existCustomer.setImgURL(customer.getImgURL());
        if(!StringUtils.isEmpty(customer.getAddress()))
            existCustomer.setAddress(customer.getAddress());
        if(!StringUtils.isEmpty(customer.getName()))
            existCustomer.setName(customer.getName());
        if(!StringUtils.isEmpty(customer.getPhone()))
            existCustomer.setPhone(customer.getPhone());
        Customer result = customerService.updateCustomer(existCustomer);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
