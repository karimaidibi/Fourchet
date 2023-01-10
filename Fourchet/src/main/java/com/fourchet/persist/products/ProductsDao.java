package com.fourchet.persist.products;


import com.fourchet.products.Product;
import com.fourchet.persist.Dao;
import com.fourchet.users.actitvities.Activity;

import java.util.List;
import java.util.Optional;

public abstract class ProductsDao implements Dao<Product> {


    @Override
    public Optional<Product> get(long id) {
        return Optional.empty();
    }

    public abstract Product findByOwnerAndName(String ownerEmail, String ownerActivityName, String name);

    @Override
    public void save(Product product) {

    }

    @Override
    public void update(Product product, String[] params) {

    }

    public abstract List<Product> getAllByOwner(String ownerEmail, String ownerActivityName);
}
