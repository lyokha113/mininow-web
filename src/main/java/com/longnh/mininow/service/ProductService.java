package com.longnh.mininow.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.longnh.mininow.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> getProducts();

    List<Product> getProductsOfStore(long id);

    Product getProductById(long id);

    Product saveProduct(Product product);

    void deleteProduct(long id);

}
