package com.example.easedine;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    // List to store ordered products
    private static List<Product> orderedProducts = new ArrayList<>();

    // Method to add a product to the order
    public static void addProductToOrder(Product newProduct) {
        boolean productExists = false;

        // Check if the product is already in the order list
        for (Product product : orderedProducts) {
            if (product.getName().equals(newProduct.getName())) {
                // If the product exists, increment the quantity
                product.incrementQuantity();
                productExists = true;
                break;
            }
        }

        // If the product does not exist in the order list, add it
        if (!productExists) {
            orderedProducts.add(newProduct);
        }
    }

    // Method to get the list of ordered products
    public static List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    // Method to calculate the total price of all ordered products
    public static String getTotalPrice() {
        double totalPrice = 0;
        for (Product product : orderedProducts) {
            double productPrice = Double.parseDouble(product.getPrice().replace("$", ""));
            totalPrice += productPrice * product.getQuantity();
        }
        return "$" + totalPrice;
    }

    // Optional method to clear the order list (for reset functionality)
    public static void clearOrder() {
        orderedProducts.clear();
    }
}
