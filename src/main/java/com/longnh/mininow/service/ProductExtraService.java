package com.longnh.mininow.service;

import com.longnh.mininow.model.ProductExtra;

import java.util.List;

public interface ProductExtraService {

    List<ProductExtra> getProductExtrasOfProduct(long id);

    List<ProductExtra> setProductExtrasToProduct(long id, List<ProductExtra> extras);
}
