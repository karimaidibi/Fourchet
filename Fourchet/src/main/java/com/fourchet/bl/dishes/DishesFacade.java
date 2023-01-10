package com.fourchet.bl.dishes;

import com.fourchet.dishes.Dish;
import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.dishes.DishesDao;

import java.text.ParseException;
import java.util.List;

// This class below is a singleton class (we create the facade only once)
public class DishesFacade {
    private static DishesFacade instance = null;

    private AbstractFactory abstractFactory;
    private DishesDao dishesDao;


    private DishesFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.dishesDao = abstractFactory.getDishesDao();
    }

    public static DishesFacade getInstance() {
        if (instance == null) {
            instance = new DishesFacade();
        }
        return instance;
    }

    public Dish saveDish(Dish dish)
    {
        try {
            Dish existingDish = dishesDao.findByActivityAndName(dish.getActivityOwner(), dish.getTitle());
            if (existingDish == null) {
                dishesDao.save(dish);
            }
            else {
                System.out.println("dish already registered !");
                return null;
            }
        } catch (Exception e){
            // TODO : replace this by sending the message to the UI
            System.out.println(e.getMessage());
        }
        return dish;
    }

    public void deleteDish(Dish dish) {
        dishesDao.delete(dish);
    }

    public void updateDish(Dish dish, String[] params) {
        dishesDao.update(dish, params);
    }

    public List<Dish> getAllDishes() throws ParseException {
        return dishesDao.getAll();
    }

    public void save(Dish dish) {
        dishesDao.save(dish);
    }

    public static void main(String[] args) {
        Dish dish = new Dish("activityOwner", "title", "description", "image", null, "category", null, 0.0, "emailOwner");
        DishesFacade dishesFacade = DishesFacade.getInstance();
        dishesFacade.save(dish);
        try {
            System.out.println(dishesFacade.getAllDishes());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
