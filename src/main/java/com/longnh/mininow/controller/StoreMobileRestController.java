package com.longnh.mininow.controller;

import com.longnh.mininow.model.Product;
import com.longnh.mininow.model.Store;
import com.longnh.mininow.service.ProductService;
import com.longnh.mininow.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mobile/api")
public class StoreMobileRestController {

    @Autowired
    StoreService storeService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/store/", method = RequestMethod.GET)
    public ResponseEntity getStores() {
        List<Store> stores = storeService.getStores();
        return ResponseEntity.status(HttpStatus.OK).body(stores);
    }

    @RequestMapping(value = "/store/{id}", method = RequestMethod.GET)
    public ResponseEntity getStore(@PathVariable("id") long id) {
        Store store = storeService.getStoreById(id);
        if (store == null ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Store isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(store);
    }

    @RequestMapping(value = "/store/{id}/product/", method = RequestMethod.GET)
    public ResponseEntity getProductsOfStore(@PathVariable("id") long id) {
        List<Product> products = productService.getProductsOfStore(id);
        if (products == null ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Store isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @RequestMapping(value = "/store/new/", method = RequestMethod.GET)
    public ResponseEntity getNewStores() {
        List<Store> stores = storeService.getNewStore();
        return ResponseEntity.status(HttpStatus.OK).body(stores);
    }

    @RequestMapping(value = "/store/find/{name}", method = RequestMethod.GET)
    public ResponseEntity findStores(@PathVariable String name) {
        List<Store> stores = storeService.findStore(name);
        return ResponseEntity.status(HttpStatus.OK).body(stores);
    }
}
