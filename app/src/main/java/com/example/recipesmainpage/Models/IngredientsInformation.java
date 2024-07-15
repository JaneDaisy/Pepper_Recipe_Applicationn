package com.example.recipesmainpage.Models;

public class IngredientsInformation {
    private final String name;
    private final int imageResId;
    private final int unitPrice;

    public IngredientsInformation(String name, int imageResId, int unitPrice) {
        this.name = name;
        this.imageResId = imageResId;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getPrice() { return unitPrice;}
}
