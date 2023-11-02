package com.zybooks.mobile2appinventoryapp;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.content.ContentValues;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton, createAccountButton;
    private DatabaseHelper databaseHelper;
    private SmsHandler smsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize database helper and SMS Handler
        databaseHelper = new DatabaseHelper(this);
        smsHandler = new SmsHandler(this);

        // Initialize UI elements
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);

        // Set click listeners for buttons
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                boolean success = performLogin(username, password);
                if (success) {
                    // Show permission request view
                    findViewById(R.id.activity_login).setVisibility(View.GONE);
                    LinearLayout permissionRequestView = findViewById(R.id.activity_permission);
                    permissionRequestView.setVisibility(View.VISIBLE);
                    // Set listener for Request Permission button inside here
                    Button requestPermissionButton = findViewById(R.id.requestPermissionButton);
                    requestPermissionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            smsHandler.checkForSmsPermission();
                        }
                    });
                    Button acceptPermissionButton = findViewById(R.id.requestPermissionButton);
                    acceptPermissionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "Permissions accepted. You will receive SMS notifications.", Toast.LENGTH_SHORT).show();

                            // Navigate to HomeActivity
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle create account button click
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                boolean success = createUserAccount(username, password);
                if (success) {
                    // Handle successful account creation
                    Toast.makeText(MainActivity.this, "Account created successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failed account creation
                    Toast.makeText(MainActivity.this, "Account creation failed. Username already exists.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Set listener for Reject Permission button
        Button rejectPermissionButton = findViewById(R.id.rejectPermissionButton);
        rejectPermissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Permissions rejected. You won't receive SMS notifications.", Toast.LENGTH_SHORT).show();

                // Navigate to HomeActivity
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

        public boolean performLogin (String username, String password) {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.COLUMN_ID};
            String selection = DatabaseHelper.COLUMN_USERNAME + " = ? AND " +
                    DatabaseHelper.COLUMN_PASSWORD + " = ?";
            String[] selectionArgs = {username, password};
            Cursor cursor = db.query(
                    DatabaseHelper.TABLE_USERS,
                    columns,
                    selection,
                    selectionArgs,
                    null, null, null
            );
            boolean success = cursor.moveToFirst();
            cursor.close();
            db.close();
            return success;
        }

        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            smsHandler.handlePermissionResult(requestCode, grantResults);
        }

        private boolean createUserAccount (String username, String password){
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            // Check if user already exists
            String[] columns = {DatabaseHelper.COLUMN_ID};
            String selection = DatabaseHelper.COLUMN_USERNAME + " = ?";
            String[] selectionArgs = {username};
            Cursor cursor = db.query(
                    DatabaseHelper.TABLE_USERS,
                    columns,
                    selection,
                    selectionArgs,
                    null, null, null
            );

            if (cursor.moveToFirst()) {
                cursor.close();
                db.close();
                return false; // User already exists
            } else {
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_USERNAME, username);
                values.put(DatabaseHelper.COLUMN_PASSWORD, password);
                long newRowId = db.insert(DatabaseHelper.TABLE_USERS, null, values);
                cursor.close();
                db.close();
                return newRowId != -1; // Account creation successful
            }
        }
    }

