package com.longnh.mininow.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.longnh.mininow.model.Product;
import com.longnh.mininow.model.ProductExtra;
import com.longnh.mininow.service.ProductService;
import com.longnh.mininow.util.ConstainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    public ResponseEntity getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity getProduct(@PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        if (product == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @RequestMapping(value = "/product/{id}/extra", method = RequestMethod.GET)
    public ResponseEntity getProductExtra(@PathVariable("id") long id) {
        Map<String, String> product = productService.getProductExtra(id);
        if (product == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @RequestMapping(value = "/product/extra", method = RequestMethod.POST)
    public ResponseEntity setProductExtra(@ModelAttribute("product") ProductExtra extra)  {
        boolean result = productService.updateProductExtra(extra.getId(), extra.getRequired(), extra.getOptional());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/product/", method = RequestMethod.POST)
    public ResponseEntity insert(@ModelAttribute("product") Product product) {
        if (product == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is empty");
        if (StringUtils.isEmpty(product.getName()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name is empty");
        if (StringUtils.isEmpty(product.getImgUrl()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product image is empty");
        if (product.getPrice() <= 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product price is empty");
        Product result = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/product/", method = RequestMethod.PUT)
    public ResponseEntity update(@ModelAttribute("product") Product product) {
        if (product == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is empty");
        if (StringUtils.isEmpty(product.getName()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name is empty");
        if (StringUtils.isEmpty(product.getImgUrl()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product image is empty");
        if (StringUtils.isEmpty(product.getId()) || product.getId() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product id is empty");
        if (product.getPrice() <= 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product price is empty");
        if (productService.getProductById(product.getId()) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product isn't existed");
        Product result = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") long id) {
        if (id <= 0) return ResponseEntity.status(500).body("Product name is empty");
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(ConstainManager.RESPONSE_SUCCESS);
    }
}
