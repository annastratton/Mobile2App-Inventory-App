package com.zybooks.mobile2appinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView dataRecyclerView;
    public DataAdapter adapter;

    private EditText addItemEditText;

    private SmsHandler smsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        smsHandler = new SmsHandler(this);


        // Button logic - go back to login screen
        Button gotoLoginButton = findViewById(R.id.gotoLoginButton);
        gotoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //data display button to add more information
        Button gotoDataDisplayButton = findViewById(R.id.gotoDataDisplayButton);
        gotoDataDisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DataDisplayActivity.class);
                startActivity(intent);
            }
        });
        //edit button
        Button editDataButton = findViewById(R.id.gotoEditData);
        editDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, InventoryManagementActivity.class);
                startActivity(intent);
            }
        });

        //delete button
        Button deleteInfoButton = findViewById(R.id.gotoDeleteData);
        deleteInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DeleteInfoActivity.class);
                startActivity(intent);
            }

            //review data logic
        });
        Button reviewDataButton = findViewById(R.id.gotoReviewData);
        reviewDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ReviewDataActivity.class);
                startActivity(intent);
            }
        });



        dataRecyclerView = findViewById(R.id.dataRecyclerView);
        dataRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        // Fetch data
        List<DataItem> dataFromDb = fetchDataFromDatabase();
        adapter = new DataAdapter(this, dataFromDb, 1);
        dataRecyclerView.setAdapter(adapter);
    }

    private List<DataItem> fetchDataFromDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        return dbHelper.getAllItems();
    }

}
