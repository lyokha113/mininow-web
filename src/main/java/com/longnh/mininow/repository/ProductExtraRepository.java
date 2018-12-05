package com.longnh.mininow.repository;

import com.longnh.mininow.model.ProductExtra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductExtraRepository extends CrudRepository<ProductExtra, Long> {

    List<ProductExtra> findAllByProduct_IdEquals(long id);
    void deleteAllByProduct_IdEquals(long id);
}
