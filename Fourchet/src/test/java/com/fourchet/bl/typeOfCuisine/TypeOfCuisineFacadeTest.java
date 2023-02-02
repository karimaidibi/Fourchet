package com.fourchet.bl.typeOfCuisine;

import com.fourchet.dishes.typeCuisine.TypeOfCuisine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeOfCuisineFacadeTest {

    @Test
    void saveTypeOfCuisine() {
        try {
            TypeOfCuisineFacade facade = TypeOfCuisineFacade.getInstance();
            TypeOfCuisine category = facade.saveTypeOfCuisine(new TypeOfCuisine("test"));
            assertTrue(category.getName().equals("test"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void findByName() {
        try {
            TypeOfCuisineFacade facade = TypeOfCuisineFacade.getInstance();
            assertTrue(facade.findByName("test").getName().equals("test"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void updateTypeOfCuisine() {
        try {
            TypeOfCuisineFacade facade = TypeOfCuisineFacade.getInstance();
            TypeOfCuisine oldCategory = new TypeOfCuisine("test");
            String[] params = {"test02"};
            facade.updateTypeOfCuisine(oldCategory, params);
            assertTrue(facade.findByName("test") == null && facade.findByName("test02").getName().equals("test02"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}