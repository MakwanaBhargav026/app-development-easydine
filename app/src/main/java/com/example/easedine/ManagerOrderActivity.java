package com.example.easedine;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagerOrderActivity extends AppCompatActivity {

    private ListView orderListLayout;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_order);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        // Get reference to the layout
        orderListLayout = findViewById(R.id.list);

        // Fetch and display orders from the database
        displayOrders();
    }

    // Method to display all orders
    private void displayOrders() {
        ArrayList<HashMap<String,String>> user_list= dbHelper.getUsers();

        ListAdapter adapter=new SimpleAdapter(this,user_list,R.layout.list_iteam,new String[]{"Username","total_price"},new int[]{R.id.username,R.id.total_price});
        orderListLayout.setAdapter(adapter);

//        if (cursor != null && cursor.getCount() > 0) {
//            while (cursor.moveToNext()) {
//                // Get order details
//                String productName = cursor.getString(cursor.getColumnIndex("product_name"));
//                int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
//                String price = cursor.getString(cursor.getColumnIndex("price"));
//                double totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
//
//                // Create a TextView for each order
//                TextView orderDetails = new TextView(this);
//                orderDetails.setText(productName + " - " + price + " x " + quantity + " = $" + totalPrice);
//                orderDetails.setTextSize(16f);
//                orderDetails.setPadding(8, 8, 8, 8);
//
//                // Add the TextView to the layout
//                orderListLayout.addView(orderDetails);
//            }
//        } else {
//            Toast.makeText(this, "No orders found", Toast.LENGTH_SHORT).show();
//        }
//
//        if (cursor != null) {
//            cursor.close();
//        }
    }
}
