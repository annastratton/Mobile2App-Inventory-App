package com.zybooks.mobile2appinventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DeleteInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private List<DataItem> dataItems;
    private DatabaseHelper dbHelper; // Better to initialize once and use

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_info);

        recyclerView = findViewById(R.id.recycler_to_delete);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1)); // Two columns

        dbHelper = new DatabaseHelper(this);
        dataItems = fetchDataFromDatabase();

        dataAdapter = new DataAdapter(this, dataItems, 1);
        dataAdapter.setDeleteMode(true);
        recyclerView.setAdapter(dataAdapter);

        // Button logic - go back to login screen
        Button gotoLoginButton = findViewById(R.id.gotoDashboard);
        gotoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteInfoActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.addDataButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedItems();
            }

        });
    }

    // Fetching data from the database
    private List<DataItem> fetchDataFromDatabase() {
        return dbHelper.getAllItems();
    }



    // Delete selected items
    private void deleteSelectedItems() {
        // List to hold items to be deleted
        List<DataItem> itemsToDelete = new ArrayList<>();

        // Identify items to delete
        for (DataItem item : dataItems) {
            if (item.isSelected()) {
                itemsToDelete.add(item);
            }
        }

        // Now, remove them from the database and list
        for (DataItem item : itemsToDelete) {
            dbHelper.deleteItem(item.getId());
            dataItems.remove(item);
        }

        if (!itemsToDelete.isEmpty()) {
            Toast.makeText(this, "Deleted selected items", Toast.LENGTH_SHORT).show();

            // Notify the adapter
            dataAdapter.notifyDataSetChanged();
        }
    }
}
