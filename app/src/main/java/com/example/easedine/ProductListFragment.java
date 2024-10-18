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

public class ProductListFragment extends Fragment {

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        // Initialize the product list and load data
        productList = new ArrayList<>();
        loadProducts();

        // Set up the adapter with the product list
        productAdapter = new ProductAdapter(getActivity(), productList);
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
        productList.add(new Product(R.drawable.p1, "Butter Roti", "$10"));
        productList.add(new Product(R.drawable.p2, "Butter Tanduri", "$20"));
        productList.add(new Product(R.drawable.p3, "Butter Nan", "$30"));
        productList.add(new Product(R.drawable.p1, "Rice", "$50"));
        productList.add(new Product(R.drawable.p2, "Jeera Rice", "$60"));
        productList.add(new Product(R.drawable.p3, "Daal", "$40"));
        productList.add(new Product(R.drawable.p1, "Daalfry", "$50"));
        productList.add(new Product(R.drawable.p2, "Paneer Tadka", "$80"));
        productList.add(new Product(R.drawable.p3, "Paneer Masala", "$70"));
        productList.add(new Product(R.drawable.p1, "Paneer Handi", "$80"));
        productList.add(new Product(R.drawable.p2, "Paneer Tufani", "$90"));
        productList.add(new Product(R.drawable.p3, "Mutter Paneer", "$70"));
        // Add more products as needed
    }
}
