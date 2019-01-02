package com.imooc.product.consumer.service.impl;

import com.imooc.product.consumer.dataobject.ProductCategory;
import com.imooc.product.consumer.repository.ProductCategoryRepository;
import com.imooc.product.consumer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
