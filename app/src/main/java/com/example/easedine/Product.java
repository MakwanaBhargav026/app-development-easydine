package com.example.easedine;

public class Product {

    private int imageResource;
    private String name;
    private String price;
    private int quantity;

    public Product(int imageResource, String name, String price) {
        this.imageResource = imageResource;
        this.name = name;
        this.price = price;
        this.quantity = 1;  // Default quantity is 1 when the product is first ordered
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }
}
