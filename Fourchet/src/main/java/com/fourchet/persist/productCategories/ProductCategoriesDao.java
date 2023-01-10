package com.fourchet.persist.productCategories;


import com.fourchet.products.ProductCategory;
import com.fourchet.persist.Dao;

import java.util.Optional;

public abstract class ProductCategoriesDao implements Dao<ProductCategory> {


    @Override
    public Optional<ProductCategory> get(long id) {
        return Optional.empty();
    }

    @Override
    public void save(ProductCategory productCategory) {

    }

    @Override
    public void update(ProductCategory productCategory, String[] params) {

    }

    @Override
    public void delete(ProductCategory productCategory) {

    }

    public abstract ProductCategory findByName(String name);
}
