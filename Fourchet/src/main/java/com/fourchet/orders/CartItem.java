package com.fourchet.orders;

import org.bson.Document;

import java.util.HashMap;

public class CartItem {

    private HashMap<Object,Object> product = new HashMap<>();
    private int quantity;

    public CartItem(HashMap<Object,Object> product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem(Document document){
        Document product = (Document) document.get("product");
        // for key and value in the product document add them to the product hashmap
        for(String key : product.keySet()){
            this.product.put(key, product.get(key));
        }
        this.quantity = document.getInteger("quantity");
    }

    public HashMap<Object,Object> getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
