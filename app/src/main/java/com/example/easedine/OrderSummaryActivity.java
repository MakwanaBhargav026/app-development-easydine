package com.example.easedine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class OrderSummaryActivity extends AppCompatActivity {

    private LinearLayout orderListLayout;
    private TextView totalPriceTextView;
    private Button orderButton;
    private DBHelper dbHelper;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
         username = sharedPreferences.getString("username", null);

        // Get references to the layout, TextView, and Button
        orderListLayout = findViewById(R.id.order_list_layout);
        totalPriceTextView = findViewById(R.id.total_price);
        orderButton = findViewById(R.id.order_button);

        // Get the list of ordered products
        List<Product> orderedProducts = OrderManager.getOrderedProducts();

        // Display each ordered product's name, price, quantity, and total price
        for (Product product : orderedProducts) {
            TextView productDetails = new TextView(this);

            double productPrice = Double.parseDouble(product.getPrice().replace("$", ""));
            double totalProductPrice = productPrice * product.getQuantity();

            productDetails.setText(product.getName() + " - " + product.getPrice() +
                    " x " + product.getQuantity() + " = $" + totalProductPrice);
            productDetails.setTextSize(16f);
            productDetails.setPadding(8, 8, 8, 8);
            orderListLayout.addView(productDetails);
        }

        // Display the total price for all products
        totalPriceTextView.setText("Total Price: " + OrderManager.getTotalPrice());

        // Set click listener for the Order button
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveOrderToDatabase();
            }
        });
    }

    // Method to save the order to the database
    private void saveOrderToDatabase() {

        List<Product> orderedProducts = OrderManager.getOrderedProducts();

        // Get the total price as a String
        String totalPrice = OrderManager.getTotalPrice(); // Assuming this returns a formatted string (e.g., "$50.00")


            boolean isInserted = dbHelper.insertUser(username,totalPrice);
            if (!isInserted) {
                Toast.makeText(OrderSummaryActivity.this, "Failed to save order", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OrderSummaryActivity.this, Page2.class);
                startActivity(intent);
                finish();
                return;
            }


        // If all products were saved successfully
        Toast.makeText(OrderSummaryActivity.this, "Order saved successfully", Toast.LENGTH_SHORT).show();

        // Navigate back to MainActivity
        Intent intent = new Intent(OrderSummaryActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
