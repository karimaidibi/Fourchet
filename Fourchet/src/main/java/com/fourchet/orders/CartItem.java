package com.fourchet.orders;

import com.fourchet.products.Product;
import org.bson.Document;

import java.text.ParseException;
import java.util.HashMap;

public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem(Document document) throws ParseException {
        Document product = (Document) document.get("product");
        // for each value of a key in the product document add it to the corresponding attribute in the product class
        this.product = new Product(product);
        this.quantity = document.getInteger("quantity");
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Document getProductDocument() {
        return product.getDocument();
    }
}
