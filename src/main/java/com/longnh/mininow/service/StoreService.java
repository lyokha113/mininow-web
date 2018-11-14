package com.longnh.mininow.service;

import com.longnh.mininow.model.OrderPlaced;
import com.longnh.mininow.model.Product;
import com.longnh.mininow.model.Store;


import java.util.List;

public interface StoreService {

    public List<Store> getStores();

    public Store getStoreById(long id);

    public List<Product> getProductsOfStore(long id);

    public List<OrderPlaced> getOrdersOfStore(long id);

    public List<Store> getNewStore();

}
