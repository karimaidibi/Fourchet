package com.fourchet.bl.orders;
import com.fourchet.bl.account.UserFacade;
import com.fourchet.orders.Cart;
import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.account.UserDao;
import com.fourchet.products.Product;
import com.fourchet.users.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

// this class is a singleton class (we create the facade only once)
public class CartFacade {
    private static CartFacade instance = null;

    private User currentUser = null;

    // The UserDaoFactory
    private AbstractFactory abstractFactory;
    // The UserDao
    private UserDao userDao;

    private Cart cart = new Cart();

    private CartFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.userDao = abstractFactory.getUserDao();
        this.currentUser = UserFacade.getInstance().getCurrentUser();
    }

    public static CartFacade getInstance() {
        if (instance == null) {
            instance = new CartFacade();
        }
        return instance;
    }

    /**
     * addItemToCart method called when the user clicks on the "Add to cart" button
     * or when the user clicks on the + button
     * if the product is not in the cart, it adds it to the cart with the quantity 1
     * if the product is in the cart, it increments the quantity of the product in the cart
     * */
    public void addProductToCart(Product product) throws ParseException {
        this.cart.addProduct(product);
        this.updateCart(this.cart);
    }

    // Tell the dao to update the cart
    public void updateCart(Cart cart) {
        this.userDao.updateCart(currentUser, cart);
    }

    // Tell the dao to get the cart from the user and handle exception if cart is null
    public Cart getCart(User user) throws ParseException {
        Cart cart = this.userDao.getCart(user);
        if (cart == null) {
            this.cart = new Cart();
        }else{
            this.cart = cart;
        }
        return this.cart;
    }

    public void updateDeliveryAddress(String deliveryAddress) {
        this.userDao.updateDeliveryAddress(currentUser, deliveryAddress);
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void deleteCart() {
        this.userDao.deleteCart(currentUser);
    }
}
