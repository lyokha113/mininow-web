package com.longnh.mininow.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.longnh.mininow.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    public List<Product> getProducts();

    public Product getProductById(long id);

    public Product saveProduct(Product product);

    public void deleteProduct(long id);

    public Map<String, String> getProductExtra(long id);

    public boolean updateProductExtra(long id, String required, String optional);
}
