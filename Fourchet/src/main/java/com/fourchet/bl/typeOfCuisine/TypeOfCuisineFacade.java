package com.fourchet.bl.typeOfCuisine;

import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.typeOfCuisine.TypeOfCuisineDao;
import com.fourchet.dishes.typeCuisine.TypeOfCuisine;

import java.text.ParseException;
import java.util.List;

// This class below is a singleton class (we create the facade only once)
public class TypeOfCuisineFacade {
    private static TypeOfCuisineFacade instance = null;

    // The UserDaoFactory
    private AbstractFactory abstractFactory;
    // The UserDao
    private TypeOfCuisineDao typeOfCuisineDao;


    private TypeOfCuisineFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.typeOfCuisineDao = abstractFactory.getTypeOfCuisineDao();
    }

    public static TypeOfCuisineFacade getInstance() {
        if (instance == null) {
            instance = new TypeOfCuisineFacade();
        }
        return instance;
    }

    // delegate the Ingredient dao to save the user
    public TypeOfCuisine saveTypeOfCuisine(TypeOfCuisine typeOfCuisine)
    {
        try {
            TypeOfCuisine existingTypeOfCuisine = typeOfCuisineDao.findByName(typeOfCuisine.getName());
            if (existingTypeOfCuisine == null) {
                typeOfCuisineDao.save(typeOfCuisine);
            }
            else {
                System.out.println("Type Of Cuisine already registered !");
                return null;
            }
        } catch (Exception e){
            // TODO : replace this by sending the message to the UI
            System.out.println(e.getMessage());
        }
        return typeOfCuisine;
    }

    public void deleteTypeOfCuisine(TypeOfCuisine typeOfCuisine) {
        typeOfCuisineDao.delete(typeOfCuisine);
    }

    public void updateTypeOfCuisine(TypeOfCuisine typeOfCuisine, String[] params) {
        typeOfCuisineDao.update(typeOfCuisine, params);
    }

    public TypeOfCuisine findByName(String name) {
        return typeOfCuisineDao.findByName(name);
    }

    public List<TypeOfCuisine> getAllTypeOfCuisine() throws ParseException {
        return typeOfCuisineDao.getAll();
    }

}
