package com.example.recipesmainpage;

import java.io.Serializable;

public class CartItem implements Serializable {
    private final String name;
    private final int imageResId;
    private int quantity;
    private final double unitPrice;
    private double totalPrice;

    public CartItem(String name, int imageResId, int quantity, double unitPrice, double totalPrice) {
        this.name = name;
        this.imageResId = imageResId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = this.unitPrice * this.quantity;
    }

    public double getTotalPrice() { return totalPrice; }

    public double getUnitPrice() { return unitPrice; }

    public int getImageResource() {return imageResId; }
}
