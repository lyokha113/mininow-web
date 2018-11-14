package com.longnh.mininow.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.longnh.mininow.model.Product;
import com.longnh.mininow.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Map<String, String> getProductExtra(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Map<String, String> extra = new HashMap<>();
            String required = product.get().getRequired();
            if (required != null) extra.put("required", required);
            String optional = product.get().getOptional();
            if (required != null) extra.put("optional", optional);
            return extra;
        }
        return null;
    }

    @Override
    public boolean updateProductExtra(long id, String required, String optional) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setRequired(required);
            product.get().setOptional(optional);
            productRepository.save(product.get());
            return true;
        } else return false;
    }

}
