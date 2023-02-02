package com.fourchet.persist.typeOfCuisine;


import com.fourchet.persist.Dao;
import com.fourchet.dishes.typeCuisine.TypeOfCuisine;

import java.util.Optional;

public abstract class TypeOfCuisineDao implements Dao<TypeOfCuisine> {


    @Override
    public Optional<TypeOfCuisine> get(long id) {
        return Optional.empty();
    }

    @Override
    public void save(TypeOfCuisine typeOfCuisine) {

    }

    @Override
    public void update(TypeOfCuisine typeOfCuisine, String[] params) {

    }

    @Override
    public void delete(TypeOfCuisine typeOfCuisine) {

    }

    public abstract TypeOfCuisine findByName(String name);
}
