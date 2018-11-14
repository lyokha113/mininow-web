package com.longnh.mininow.service;

import com.longnh.mininow.model.OrderPlaced;
import com.longnh.mininow.model.Product;
import com.longnh.mininow.model.Store;
import com.longnh.mininow.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Store getStoreById(long id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.orElse(null);
    }

    @Override
    public List<Product> getProductsOfStore(long id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.map(store1 -> new ArrayList<>(store1.getProducts())).orElse(null);
    }

    @Override
    public List<OrderPlaced> getOrdersOfStore(long id) {
//        Optional<Store> store = storeRepository.findById(id);
////        return store.map(store1 -> new ArrayList<>(store1())).orElse(null);
        return null;
    }

}
