package com.example.easedine;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment1 extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private Button myOrderButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);

        // Set the RecyclerView to a vertical layout
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        // Initialize the product list and load data
        productList = new ArrayList<>();
        loadProducts();

        // Set up the adapter with the product list
        productAdapter = new ProductAdapter(getActivity(), productList); // Pass context and product list to the adapter
        recyclerView.setAdapter(productAdapter);

        // Initialize the "My Order" button
        myOrderButton = view.findViewById(R.id.button2);

        // Set OnClickListener for "My Order" button
        myOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to OrderSummaryActivity
                Intent intent = new Intent(getActivity(), OrderSummaryActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    // Sample products to populate the RecyclerView
    private void loadProducts() {
        productList.add(new Product(R.drawable.p1, "Dry Manchurian", "$10"));
        productList.add(new Product(R.drawable.p2, "Manchurian", "$20"));
        productList.add(new Product(R.drawable.p3, "Manchau soup", "$30"));
        productList.add(new Product(R.drawable.p1, "Tomato soup", "$50"));
        productList.add(new Product(R.drawable.p2, "Hakka Noodles", "$60"));
        productList.add(new Product(R.drawable.p3, "Manchurian Rice", "$40"));
        productList.add(new Product(R.drawable.p1, "Manchurina Noodles", "$50"));
        productList.add(new Product(R.drawable.p2, "Manchurian Bhel", "$80"));
        productList.add(new Product(R.drawable.p3, "Paneer Chilly", "$70"));
//        productList.add(new Product(R.drawable.p1, "Paneer Handi", "$80"));
//        productList.add(new Product(R.drawable.p2, "Paneer Tufani", "$90"));
//        productList.add(new Product(R.drawable.p3, "Mutter Paneer", "$70"));
        // Add more products as needed
    }

}
