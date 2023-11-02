package com.zybooks.mobile2appinventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;

public class DataDisplayActivity extends AppCompatActivity {

    private EditText addItemEditText;

    private EditText addQtyEditText;
    private Button addDataButton;
    private RecyclerView dataRecyclerView;
    private DataAdapter dataAdapter;
    private List<DataItem> dataItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        // Initialize the views
        addItemEditText = findViewById(R.id.addItemEditText);
        addDataButton = findViewById(R.id.addDataButton);
        addQtyEditText = findViewById(R.id.addItemQty);
        dataRecyclerView = findViewById(R.id.dataRecyclerView);
        EditText addItemQtyEditText = findViewById(R.id.addItemQty);


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dataItems = dbHelper.getAllItems();

        dataAdapter = new DataAdapter(this, dataItems, 1);
        dataRecyclerView.setAdapter(dataAdapter);
        dataRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = addItemEditText.getText().toString().trim();
                String qtyName = addQtyEditText.getText().toString().trim();
                int itemQuantity = Integer.parseInt(addItemQtyEditText.getText().toString().trim());  // Convert the string quantity to int
                DataItem newItem = new DataItem(itemName, itemQuantity);
                DatabaseHelper dbHelper = new DatabaseHelper(DataDisplayActivity.this);
                long id = dbHelper.insertItem(newItem);
                if(id != -1) {
                    dataItems.add(newItem);
                    dataAdapter.notifyDataSetChanged();
                    addItemEditText.setText("");
                    addItemQtyEditText.setText(""); // Clear the quantity EditText
                } else {
                    Toast.makeText(DataDisplayActivity.this, "Error adding item.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Button logic
        Button gotoLoginButton = findViewById(R.id.gotoDashboard);
        gotoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataDisplayActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
