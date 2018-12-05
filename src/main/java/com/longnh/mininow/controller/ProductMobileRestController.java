package com.longnh.mininow.controller;


import com.longnh.mininow.model.ProductExtra;
import com.longnh.mininow.service.ProductExtraService;
import com.longnh.mininow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/mobile/api")
public class ProductMobileRestController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductExtraService productExtraService;

    @RequestMapping(value = "/product/{id}/extra", method = RequestMethod.GET)
    public ResponseEntity getProductExtra(@PathVariable("id") long id) {
        List<ProductExtra> extras = productExtraService.getProductExtrasOfProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(extras);
    }


}
