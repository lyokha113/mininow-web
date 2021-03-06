package com.longnh.mininow.repository;

import com.longnh.mininow.model.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {

    List<Store> findTop10ByOrderByRegisterTimeDesc();
    List<Store> findByNameContains(String name);
}
