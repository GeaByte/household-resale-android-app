package com.example.g1_csis3175_002;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelperSeller extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "your_database_name.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "items";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_SELL_RENT = "sell_rent";
    private static final String COLUMN_IMAGE_PATH = "image_path";
    public DatabaseHelperSeller(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_SELL_RENT + " TEXT, " +
                COLUMN_IMAGE_PATH + " TEXT)";
        db.execSQL(createTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public ArrayList<ProductModel> getAllProducts() {
        ArrayList<ProductModel> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_TITLE,
                COLUMN_DESCRIPTION,
                COLUMN_PRICE,
                COLUMN_IMAGE_PATH
        };

        Cursor cursor = db.query(
                TABLE_NAME,   // The table to query
                projection,   // The array of columns to return (pass null to get all)
                null,         // The columns for the WHERE clause
                null,         // The values for the WHERE clause
                null,         // don't group the rows
                null,         // don't filter by row groups
                null          // The sort order
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE));
                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_PATH));

                ProductModel product = new ProductModel(title, price, imagePath, description);
                productList.add(product);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return productList;
    }


    public boolean insertItem(String title, String description, String price, String location, String category, String sellRent, String imagePath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_PRICE, price);
        contentValues.put(COLUMN_LOCATION, location);
        contentValues.put(COLUMN_CATEGORY, category);
        contentValues.put(COLUMN_SELL_RENT, sellRent);
        contentValues.put(COLUMN_IMAGE_PATH, imagePath);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public ArrayList<ProductModel> searchProducts(String query) {
        ArrayList<ProductModel> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_TITLE,
                COLUMN_DESCRIPTION,
                COLUMN_PRICE,
                COLUMN_IMAGE_PATH
        };

        String selection = COLUMN_TITLE + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + query + "%"};

        Cursor cursor = db.query(
                TABLE_NAME,   // The table to query
                projection,   // The array of columns to return (pass null to get all)
                selection,    // The columns for the WHERE clause
                selectionArgs, // The values for the WHERE clause
                null,         // don't group the rows
                null,         // don't filter by row groups
                null          // The sort order
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE));
                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_PATH));

                ProductModel product = new ProductModel(title, price, imagePath, description);
                productList.add(product);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return productList;
    }

}
