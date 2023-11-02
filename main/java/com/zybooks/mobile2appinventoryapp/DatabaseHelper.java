package com.zybooks.mobile2appinventoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    public List<DataItem> getAllItems() {
        List<DataItem> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ITEMS, null);
        if (cursor.moveToFirst()) {
            int itemNameIndex = cursor.getColumnIndex(COLUMN_ITEM_NAME);
            int quantityIndex = cursor.getColumnIndex(COLUMN_QUANTITY);
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            if(itemNameIndex != -1 && quantityIndex != -1) { // Ensure the columns exist
                do {
                    DataItem item = new DataItem(
                            cursor.getString(itemNameIndex),
                            cursor.getInt(quantityIndex)
                    );
                    item.setId(cursor.getInt(idIndex));
                    items.add(item);
                } while (cursor.moveToNext());
            } else {


            }
        }
        cursor.close();
        db.close();
        return items;
    }


    public long insertItem(DataItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_NAME, item.getName());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        long id = db.insert(TABLE_ITEMS, null, values);
        db.close();
        return id;
    }

    public static final String DATABASE_NAME = "app_database";
    public static final int DATABASE_VERSION = 1;

    // Users Table
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // Items Table
    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_QUANTITY = "quantity";

    // SQL statement for creating users table
    private static final String TABLE_CREATE_USERS = "CREATE TABLE " + TABLE_USERS + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " TEXT NOT NULL, "
            + COLUMN_PASSWORD + " TEXT NOT NULL);";

    // SQL statement for creating items table
    private static final String TABLE_CREATE_ITEMS = "CREATE TABLE " + TABLE_ITEMS + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ITEM_NAME + " TEXT, "
            + COLUMN_QUANTITY + " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_ITEMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    public int updateItem(DataItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ITEM_NAME, item.getName());
        values.put(COLUMN_QUANTITY, item.getQuantity());

        // Update row
        return db.update(TABLE_ITEMS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(item.getId())});
    }

    public int deleteItem(int itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_ITEMS, COLUMN_ID + " = ?", new String[]{String.valueOf(itemId)});
        db.close();
        return rowsAffected;
    }

}
