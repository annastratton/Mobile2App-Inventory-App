package com.zybooks.mobile2appinventoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.content.ContentValues;
import android.widget.Toast;

import java.util.List;


public class ReviewDataActivity extends AppCompatActivity {

    private RecyclerView dataRecyclerView;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_inventory);

        dataRecyclerView = findViewById(R.id.dataRecyclerView);
        dataRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        // Fetch data
        List<DataItem> dataFromDb = fetchDataFromDatabase();
        adapter = new DataAdapter(this, dataFromDb, 1);
        dataRecyclerView.setAdapter(adapter);


        //button logic to go back to Dashboard
        Button gotoLoginButton = findViewById(R.id.gotoDashboard);
        gotoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewDataActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private List<DataItem> fetchDataFromDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        return dbHelper.getAllItems();
    }
}

