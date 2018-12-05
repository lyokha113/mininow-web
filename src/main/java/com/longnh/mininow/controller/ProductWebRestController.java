package com.longnh.mininow.controller;


import com.longnh.mininow.model.Product;
import com.longnh.mininow.model.ProductExtra;
import com.longnh.mininow.service.ProductExtraService;
import com.longnh.mininow.service.ProductService;
import com.longnh.mininow.util.ConstainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProductWebRestController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductExtraService productExtraService;

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
        List<ProductExtra> extras = productExtraService.getProductExtrasOfProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(extras);
    }

    @RequestMapping(value = "/product/{id}/extra", method = RequestMethod.POST)
    public ResponseEntity setProductExtra(@PathVariable("id") long id, @RequestBody List<ProductExtra> extras) {
        List<ProductExtra> result = productExtraService.setProductExtrasToProduct(id, extras);
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
