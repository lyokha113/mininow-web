package com.longnh.mininow.controller;


import com.longnh.mininow.model.Product;
import com.longnh.mininow.service.ProductService;
import com.longnh.mininow.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class StoreWebRestController {

    @Autowired
    StoreService storeService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/store/{id}/product/", method = RequestMethod.GET)
    public ResponseEntity getProductsOfStore(@PathVariable("id") long id) {
        List<Product> products = productService.getProductsOfStore(id);
        if (products == null ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Store isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
