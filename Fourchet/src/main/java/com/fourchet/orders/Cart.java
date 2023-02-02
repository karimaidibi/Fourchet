package com.fourchet.orders;

import com.fourchet.products.Product;
import org.bson.Document;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {

    private float orderPrice;
    private final float deliveryPrice = 5.0f;
    private float totalPrice;
    private final String currency = "USD";
    private int totalQuantityOfProducts;

    private String deliveryAddress;

    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(List<Document> items, String deliveryAddress) throws ParseException {
        this.items = new ArrayList<>();
        for(Document item : items){
            this.items.add(new CartItem(item));
        }
        this.deliveryAddress = deliveryAddress;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public float getOrderPrice() {
        if(this.items.size() == 0){
            return 0;
        }else{
            float orderPrice = 0;
            for(CartItem item : this.items){
                orderPrice += (int)item.getProduct().getPrice() * item.getQuantity();
            }
            return orderPrice;
        }
    }

    public float getDeliveryPrice() {
        return deliveryPrice;
    }

    public float getTotalPrice() {
        if(this.items.size() == 0) {
            return 0;
        }else{
            return this.getOrderPrice() + this.getDeliveryPrice();
        }
    }

    /**
     * adds a product to the cart
     * if the product is already in the cart, it will increase the quantity
     * if the product is not in the cart, it will add it to the cart with the quantity 1
     * @param product the product to add to the cart
     * */
    public void addProduct(Product product) {
        boolean productFound = false;
        for (CartItem item : this.items) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.setQuantity(item.getQuantity() + 1);
                productFound = true;
            }
        }
        if (!productFound) {
            this.items.add(new CartItem(product, 1));
        }
    }

    /**
     * removes a product from the cart
     * if the product is in the cart, it will decrease the quantity
     * if the quantity is 0, it will remove the product from the cart
     * @param product the product to remove from the cart
     * */
    public void removeProduct(Product product) {
        for(int i = 0; i < this.items.size(); i++){
            if(this.items.get(i).getProduct().getName().equals(product.getName())){
                if(this.items.get(i).getQuantity() > 1){
                    this.items.get(i).setQuantity(this.items.get(i).getQuantity() - 1);
                }else{
                    this.items.remove(i);
                }
            }
        }
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String text) {
        this.deliveryAddress = text;
    }
}
