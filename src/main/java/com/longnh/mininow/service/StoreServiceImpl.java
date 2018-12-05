package com.longnh.mininow.service;

import com.longnh.mininow.model.Store;
import com.longnh.mininow.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public List<Store> getStores() {
        return (List<Store>) storeRepository.findAll();
    }

    @Override
    public List<Store> getNewStore() {
        return storeRepository.findTop10ByOrderByRegisterTimeDesc();
    }

    @Override
    public List<Store> findStore(String name) {
        return storeRepository.findByNameContains(name);
    }

    @Override
    public Store getStoreById(long id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.orElse(null);
    }


}
