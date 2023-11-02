package com.zybooks.mobile2appinventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InventoryManagementActivity extends AppCompatActivity {

    private RecyclerView dataRecyclerView;
    private List<DataItem> dataItems;
    private EditText addItemEditText;
    private Button addDataButton;
    private DataAdapter dataAdapter;
    private DataItem selectedItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_management);

        dataRecyclerView = findViewById(R.id.dataRecyclerView);
        addItemEditText = findViewById(R.id.addItemEditText);


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dataItems = dbHelper.getAllItems();

        dataAdapter = new DataAdapter(this, dataItems, 1);
        dataRecyclerView.setAdapter(dataAdapter);
        dataRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        dataAdapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DataItem clickedItem = dataItems.get(position);
                addItemEditText.setText(clickedItem.getName());
                selectedItem = clickedItem;
            }
        });


//button logic to go back to Dashboard
        Button gotoLoginButton = findViewById(R.id.gotoDashboard);
        gotoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryManagementActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void refreshData() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dataItems.clear();
        dataItems.addAll(dbHelper.getAllItems());
        dataAdapter.notifyDataSetChanged();
    }
}
