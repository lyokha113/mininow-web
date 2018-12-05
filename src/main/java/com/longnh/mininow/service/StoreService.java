package com.longnh.mininow.service;

import com.longnh.mininow.model.OrderPlaced;
import com.longnh.mininow.model.Product;
import com.longnh.mininow.model.Store;


import java.util.List;

public interface StoreService {

    List<Store> getStores();

    List<Store> getNewStore();

    List<Store> findStore(String name);

    Store getStoreById(long id);

}
