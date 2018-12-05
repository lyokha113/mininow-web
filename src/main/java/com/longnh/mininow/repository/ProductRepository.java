package com.longnh.mininow.repository;

import com.longnh.mininow.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAllByStore_IdEquals(long id);
}
