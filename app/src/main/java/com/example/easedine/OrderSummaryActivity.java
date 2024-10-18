package com.example.easedine;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class OrderSummaryActivity extends AppCompatActivity {

    private LinearLayout orderListLayout;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        // Get references to the layout and TextView
        orderListLayout = findViewById(R.id.order_list_layout);
        totalPriceTextView = findViewById(R.id.total_price);

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
    }
}
