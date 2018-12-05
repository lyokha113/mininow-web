package com.longnh.mininow.service;

import com.longnh.mininow.model.Product;
import com.longnh.mininow.model.ProductExtra;
import com.longnh.mininow.repository.ProductExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductExtraServiceImpl implements ProductExtraService {

    @Autowired
    ProductExtraRepository productExtraRepository;

    @Override
    public List<ProductExtra> getProductExtrasOfProduct(long id) {
        return productExtraRepository.findAllByProduct_IdEquals(id);
    }

    @Override
    public List<ProductExtra> setProductExtrasToProduct(long id, List<ProductExtra> extras) {

        List<ProductExtra> result = new ArrayList<>();
        if (extras == null) {
            return result;
        }

        Product product = new Product();
        product.setId(id);

        productExtraRepository.deleteAllByProduct_IdEquals(id);
        for (ProductExtra extra : extras) {
            extra.setProduct(product);
            result.add(productExtraRepository.save(extra));
        }

        return result;
    }
}
