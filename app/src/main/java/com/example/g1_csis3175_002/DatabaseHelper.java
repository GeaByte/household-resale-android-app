package com.example.g1_csis3175_002;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "Information.db";
    final static String TABLE1_NAME = "User";
    final static String T1COL1 = "Username";
    final static String T1COL2 = "Name";
    final static String T1COL3 = "Address";
    final static String T1COL4 = "ZipCode";
    final static String T1COL5 = "City";
    final static String T1COL6 = "Contact";
    final static String T1COL7 = "Email";
    final static String T1COL8 = "Password";
    final static String T1COL9 = "IsBuyer";
    final static String T1COL10 = "IsSeller";

    final static String TABLE2_NAME = "Product";
    final static String T2COL1 = "ProductID";
    final static String T2COL2 = "ProductName";
    final static String T2COL3 = "Description";
    final static String T2COL4 = "Price";
    final static String T2COL5 = "PickupAddress";
    final static String T2COL6 = "Category";
    final static String T2COL7 = "SellOrShare";
    final static String T2COL8 = "ImagePath";
    final static String T2COL9 = "Seller";
    final static String T2COL10 = "UploadTime";

    final static String TABLE3_NAME = "UserOrder";
    final static String T3COL1 = "OrderID";
    final static String T3COL2 = "ShippingAddress";
    final static String T3COL3 = "OrderDate";
    final static String T3COL4 = "OrderStatus";
    final static String T3COL5 = "ProductId";

    final static String TABLE4_NAME = "User_has_Order";
    final static String T4COL1 = "Username";
    final static String T4COL2 = "OrderID";
    final static String TABLE5_NAME = "Buyer_buys_Product";


    final static String T5COL1 = "Username";
    final static String T5COL2 = "ProductID";
    final static String TABLE6_NAME = "Seller_sells_Product";
    final static String T6COL1 = "Username";
    final static String T6COL2 = "ProductID";
    final static String T6COL3 = "PriceDiscount";
    final static String TABLE7_NAME = "Seller_shares_Product";
    final static String T7COL1 = "Username";
    final static String T7COL2 = "ProductID";
    final static int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryTable1 = "CREATE TABLE " + TABLE1_NAME + "(" +
                T1COL1 + " TEXT PRIMARY KEY," +
                T1COL2 + " TEXT," +
                T1COL3 + " TEXT," +
                T1COL4 + " TEXT," +
                T1COL5 + " TEXT," +
                T1COL6 + " INTEGER," +
                T1COL7 + " TEXT UNIQUE," +
                T1COL8 + " TEXT,"  +
                T1COL9 + " INTEGER DEFAULT 0," +  //have to set to 1 to become "true" when user click on the buyer_or_seller page
                T1COL10 + " INTEGER DEFAULT 0);";

        String queryTable2 = "CREATE TABLE " + TABLE2_NAME + "(" +
                T2COL1 + " INTEGER PRIMARY KEY," +
                T2COL2 + " TEXT," +
                T2COL3 + " TEXT," +
                T2COL4 + " REAL," +
                T2COL5 + " TEXT," +
                T2COL6 + " TEXT," +
                T2COL7 + " TEXT," +
                T2COL8 + " TEXT," +
                T2COL9 + " TEXT," +
                T2COL10 + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (" + T2COL9 + ") REFERENCES " + TABLE1_NAME + "(" +
                T1COL1 + ")" + ");";

        String queryTable3 = "CREATE TABLE " + TABLE3_NAME + "(" +
                T3COL1 + " INTEGER PRIMARY KEY," +
                T3COL2 + " TEXT," +
                T3COL3 + " TEXT," +
                T3COL4 + " TEXT," +
                T3COL5 + " INTEGER," +
                "FOREIGN KEY(" + T3COL5 + ") REFERENCES " + TABLE2_NAME + "(" +
                T2COL1 + ")" + ");";

        String queryTable4 = "CREATE TABLE " + TABLE4_NAME + "(" +
                T4COL1 + " TEXT," +
                T4COL2 + " INTEGER," +
                "PRIMARY KEY (" + T4COL1 + ", " + T4COL2 + ")," +
                "FOREIGN KEY (" + T4COL1+ ") REFERENCES " + TABLE1_NAME + "(" +
                T1COL1 + ")," +
                "FOREIGN KEY(" + T4COL2 + ") REFERENCES " + TABLE3_NAME + "(" +
                T3COL1 + ")" + ");";

        String queryTable5 = "CREATE TABLE " + TABLE5_NAME + "(" +
                T5COL1 + " TEXT," +
                T5COL2 + " INTEGER," +
                "PRIMARY KEY (" + T5COL1 + ", " + T5COL2 + ")," +
                "FOREIGN KEY (" + T5COL1+ ") REFERENCES " + TABLE1_NAME + "(" +
                T1COL1 + ")," +
                "FOREIGN KEY(" + T5COL2 + ") REFERENCES " + TABLE2_NAME + "(" +
                T2COL1 + ")" + ");";

        String queryTable6 = "CREATE TABLE " + TABLE6_NAME + "(" +
                T6COL1 + " TEXT," +
                T6COL2 + " INTEGER," +
                T6COL3 + " REAL," +
                "PRIMARY KEY (" + T6COL1 + ", " + T6COL2 + ")," +
                "FOREIGN KEY (" + T6COL1+ ") REFERENCES " + TABLE1_NAME + "(" +
                T1COL1 + ")," +
                "FOREIGN KEY(" + T6COL2 + ") REFERENCES " + TABLE2_NAME + "(" +
                T2COL1 + ")" + ");";

        String queryTable7 = "CREATE TABLE " + TABLE7_NAME + "(" +
                T7COL1 + " TEXT," +
                T7COL2 + " INTEGER," +
                "PRIMARY KEY (" + T7COL1 + ", " + T7COL2 + ")," +
                "FOREIGN KEY (" + T7COL1+ ") REFERENCES " + TABLE1_NAME + "(" +
                T1COL1 + ")," +
                "FOREIGN KEY(" + T7COL2 + ") REFERENCES " + TABLE2_NAME + "(" +
                T2COL1 + ")" + ");";


        sqLiteDatabase.execSQL(queryTable1);
        sqLiteDatabase.execSQL(queryTable2);
        sqLiteDatabase.execSQL(queryTable3);
        sqLiteDatabase.execSQL(queryTable4);
        sqLiteDatabase.execSQL(queryTable5);
        sqLiteDatabase.execSQL(queryTable6);
        sqLiteDatabase.execSQL(queryTable7);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE4_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE5_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE6_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE7_NAME);
        onCreate(sqLiteDatabase);
    }

    // addUser
    public boolean addUser(String username, String name, String address, String zipcode, String city,
                           int contact, String email, String password, boolean isBuyer, boolean isSeller){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(T1COL1,username);
        values.put(T1COL2,name);
        values.put(T1COL3,address);
        values.put(T1COL4, zipcode);
        values.put(T1COL5, city);
        values.put(T1COL6, contact);
        values.put(T1COL7, email);
        values.put(T1COL8, password);
        values.put(T1COL9, 0);
        values.put(T1COL10, 0);

        String sellerName = username;
        String sellerAddress = address;



        long r = sqLiteDatabase.insert(TABLE1_NAME, null, values);
        return r > 0;



    }
    public String addUser(String username, String name, String address, String zipcode, String city,
                           int contact, String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //check unique email
        try (Cursor checkUniqueEmail = sqLiteDatabase.query(TABLE1_NAME, new String[] { T1COL7 },
                T1COL7 + "=?", new String[] { email }, null, null, null)) {
            if (checkUniqueEmail.moveToFirst()) {
                return UserMessage.EMAIL_EXISTS;
            }
        }

        //check unique name
        try (Cursor checkUniqueName = sqLiteDatabase.query(TABLE1_NAME, new String[] { T1COL1 },
                T1COL1 + "=?", new String[] { username }, null, null, null)) {
            if (checkUniqueName.moveToFirst()) {
                return UserMessage.USERNAME_EXISTS;
            }
        }

        ContentValues values = new ContentValues();
        values.put(T1COL1,username);
        values.put(T1COL2,name);
        values.put(T1COL3,address);
        values.put(T1COL4, zipcode);
        values.put(T1COL5, city);
        values.put(T1COL6, contact);
        values.put(T1COL7, email);
        values.put(T1COL8, password);

        long r = sqLiteDatabase.insert(TABLE1_NAME, null, values);
        return r > 0? UserMessage.SUCCESS: UserMessage.ERROR;

    }

    public String loginUser(String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor cursor = sqLiteDatabase.query(TABLE1_NAME, new String[]{T1COL7, T1COL8},
                T1COL7 + "=? AND " + T1COL8 + "=?", new String[]{email, password}, null, null, null)) {
            if (cursor.moveToFirst()) {
                return UserMessage.SUCCESS;
            } else {
                return UserMessage.ERROR;
            }
        }
    }

    public String getUsernameByEmail(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String username = null;

        try (Cursor cursor = sqLiteDatabase.query(
                TABLE1_NAME,
                new String[]{T1COL1},
                T1COL7 + "=?",
                new String[]{email},
                null,
                null,
                null)) {

            if (cursor.moveToFirst()) {
                username = cursor.getString(cursor.getColumnIndexOrThrow(T1COL1));
            }
        }
        return username;
    }

    // getUser
    public Cursor getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE1_NAME, null, T1COL1 + "=?", new String[] { username }, null, null, null);
    }

    // get order details by order ID
    public Cursor getOrderDetails(int orderId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE3_NAME, null, T3COL1 + "=?", new String[] { String.valueOf(orderId) }, null, null, null);
    }

    // Method to update shipping address by order ID
    public boolean updateShippingAddress(int orderId, String newAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL2, newAddress);
        int rowsAffected = db.update(TABLE3_NAME, values, T3COL1 + "=?", new String[]{String.valueOf(orderId)});
        return rowsAffected > 0;
    }

    // updateUser
    public boolean updateUser(String username, String name, String address, String zipcode,
                              String city, int contact, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL2,name);
        values.put(T1COL3,address);
        values.put(T1COL4, zipcode);
        values.put(T1COL5, city);
        values.put(T1COL6, contact);
        values.put(T1COL7, email);
        values.put(T1COL8, password);

        int rowsAffected = db.update(TABLE1_NAME, values, T1COL1 + "=?", new String[] { username });
        return rowsAffected > 0;
    }

    // deleteUser
    public boolean deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE1_NAME, T1COL1 + "=?", new String[] { username });
        return rowsDeleted > 0;
    }

    public Cursor viewOrder(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = " SELECT * FROM  " + "product";
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        return c;
    }







    //2-Product
    // addProduct
    public boolean addProduct(String productName, String description, String price, String pickupAddress,
                           String category, String sellOrShare, String imagePath, String seller) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(T2COL2, productName);
            values.put(T2COL3, description);
            values.put(T2COL4, price);
            values.put(T2COL5, pickupAddress);
            values.put(T2COL6, category);
            values.put(T2COL7, sellOrShare);
            values.put(T2COL8, imagePath);
            values.put(T2COL9, seller);

            long result = db.insert(TABLE2_NAME, null, values);
            return result != -1;
        }
        catch (Exception e) {
            Log.e("DatabaseHelper", "Error inserting product: " + e.getMessage());
            return false;
        }




    }

    // getProduct join location
    public ProductModel getProduct(long productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ProductModel product = null;
        try (Cursor cursor = db.query(TABLE2_NAME, new String[] {T2COL2, T2COL3, T2COL4, T2COL8,
                T2COL9, T2COL5}, T2COL1 + "=?", new String[] {String.valueOf(productId)}, null, null, null)) {
            if (cursor.moveToFirst()) {
                String productName = cursor.getString(cursor.getColumnIndexOrThrow(T2COL2));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(T2COL4));
                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(T2COL8));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(T2COL3));
                String seller = cursor.getString(cursor.getColumnIndexOrThrow(T2COL9));
                String pickupAddress = cursor.getString(cursor.getColumnIndexOrThrow(T2COL5));

                product = new ProductModel(productName, price, imagePath, description, seller, pickupAddress);

            }
        }
        return product;
    }

    // updateProduct
    public boolean updateProduct(int productId, String productName, String description, String price, String location,
                                 String category, String sellOrShare, String imagePath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL2, productName);
        values.put(T2COL3, description);
        values.put(T2COL4, price);
        values.put(T2COL5, location);
        values.put(T2COL6, category);
        values.put(T2COL7, sellOrShare);
        values.put(T2COL8, imagePath);

        int rowsAffected = db.update(TABLE2_NAME, values, T2COL1 + "=?", new String[] {String.valueOf(productId)});
        return rowsAffected > 0;
    }

    // deleteProduct
    public boolean deleteProduct(long productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE2_NAME, T2COL1 + "=?", new String[] {String.valueOf(productId)});
        return rowsDeleted > 0;
    }

    private Cursor getProductById(int productId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] columns = {T2COL2, T2COL3, T2COL4, T2COL8, T2COL9, T2COL5};
        String selection = T2COL1 + "=?";
        String[] selectionArgs = {String.valueOf(productId)};

        return sqLiteDatabase.query(TABLE2_NAME, columns, selection, selectionArgs, null, null, null);
    }

//    public String getPickupAddressByUsername(String username) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String pickupAddress = null;
//
//        try (Cursor cursor = db.query(
//                TABLE1_NAME,
//                new String[]{T1COL4},
//                T1COL1 + "=?",
//                new String[]{username},
//                null,
//                null,
//                null)) {
//
//            if (cursor.moveToFirst()) {
//                pickupAddress = cursor.getString(cursor.getColumnIndexOrThrow(T1COL4));
//            }
//        } catch (Exception e) {
//            Log.e("DatabaseHelper", "Error getting pickup address: " + e.getMessage());
//        }
//
//        return pickupAddress;
//    }


    public boolean addOrder(Integer oId, String address, String date, String status, int productID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL1,oId);
        values.put(T3COL2, address);
        values.put(T3COL3,date);
        values.put(T3COL4, status);
        values.put(T3COL5, productID);

        long r = sqLiteDatabase.insert(TABLE2_NAME, null, values);

        if (r != -1) {
            Cursor cursor = getProductById(productID);
            if (cursor != null && cursor.moveToFirst()) {
                @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex(T2COL2));
                @SuppressLint("Range") String productDescription = cursor.getString(cursor.getColumnIndex(T2COL3));
                @SuppressLint("Range") double productPrice = cursor.getDouble(cursor.getColumnIndex(T2COL4));
                @SuppressLint("Range") String productImagePath = cursor.getString(cursor.getColumnIndex(T2COL8));
                cursor.close();

                ContentValues orderProductValues = new ContentValues();
                orderProductValues.put(T5COL1, oId);
                orderProductValues.put(T5COL2, productID);
                long result = sqLiteDatabase.insert(TABLE5_NAME, null, orderProductValues);

                if(result != -1) {
                    // Do something with the retrieved product information if needed
                    return true;
                }
            }
        }
        return false;
    }


    public ArrayList<ProductModel> getAllProducts() {
        ArrayList<ProductModel> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                T2COL1,
                T2COL2,
                T2COL3,
                T2COL4,
                T2COL5,
                T2COL8,
                T2COL9,
                T2COL10,
        };

        Cursor cursor = db.query(
                TABLE2_NAME,   // The table to query
                projection,   // The array of columns to return (pass null to get all)
                null,         // The columns for the WHERE clause
                null,         // The values for the WHERE clause
                null,         // don't group the rows
                null,         // don't filter by row groups
                null          // The sort order
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int productID = cursor.getInt(cursor.getColumnIndexOrThrow(T2COL1));
                String productName = cursor.getString(cursor.getColumnIndexOrThrow(T2COL2));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(T2COL3));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(T2COL4));
                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(T2COL8));
                String seller = cursor.getString(cursor.getColumnIndexOrThrow(T2COL9));
                String pickupAddress = cursor.getString(cursor.getColumnIndexOrThrow(T2COL5));
                String uploadTime = cursor.getString(cursor.getColumnIndexOrThrow(T2COL10));

                ProductModel product = new ProductModel(productID, productName, price, imagePath,
                        description, seller, pickupAddress, uploadTime);
                productList.add(product);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return productList;
    }

    public ArrayList<ProductModel> searchProducts(String query) {
        ArrayList<ProductModel> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                T2COL2,
                T2COL3,
                T2COL4,
                T2COL8,
                T2COL9
        };

        String selection = T2COL2 + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + query + "%"};

        Cursor cursor = db.query(
                TABLE2_NAME,   // The table to query
                projection,   // The array of columns to return (pass null to get all)
                selection,    // The columns for the WHERE clause
                selectionArgs, // The values for the WHERE clause
                null,         // don't group the rows
                null,         // don't filter by row groups
                null          // The sort order
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(cursor.getColumnIndexOrThrow(T2COL2));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(T2COL3));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(T2COL4));
                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(T2COL8));
                String seller = cursor.getString(cursor.getColumnIndexOrThrow(T2COL9));
                String pickupAddress = cursor.getString(cursor.getColumnIndexOrThrow(T2COL5));

                ProductModel product = new ProductModel(productName, price, imagePath,
                        description, seller, pickupAddress);
                productList.add(product);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return productList;
    }


    public ArrayList<String> getOrderItemsByOrderId(int orderId) {
        ArrayList<String> orderItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {T5COL2}; // Assuming T5COL2 is the column storing ProductID in User_buys_Product table

        String selection = T5COL1 + "=?";
        String[] selectionArgs = new String[]{String.valueOf(orderId)};

        Cursor cursor = db.query(
                TABLE5_NAME,   // The table to query
                projection,   // The array of columns to return (pass null to get all)
                selection,    // The columns for the WHERE clause
                selectionArgs, // The values for the WHERE clause
                null,         // don't group the rows
                null,         // don't filter by row groups
                null          // The sort order
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int productId = cursor.getInt(cursor.getColumnIndexOrThrow(T5COL2));
                String productName = getProduct(productId).getProductName();
                orderItems.add(productName);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return orderItems;
    }

    public void resetDatabase(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE4_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE5_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE6_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE7_NAME);

        onCreate(db);
        insertFakeData(db);
    }

    private void insertFakeData(SQLiteDatabase db) {
        //insert user table
        for (int i = 1; i <= 5; i++) {
            ContentValues userValues = new ContentValues();
            userValues.put(T1COL1, "username" + i);
            userValues.put(T1COL2, "Name" + i);
            userValues.put(T1COL3, "Address" + i);
            userValues.put(T1COL4, "ZipCode" + i);
            userValues.put(T1COL5, "City" + i);
            userValues.put(T1COL6, 12345 + i);
            userValues.put(T1COL7, i + "@com");
            userValues.put(T1COL8, i);
            userValues.put(T1COL9, 0);
            userValues.put(T1COL10, 0);
            db.insert(TABLE1_NAME, null, userValues);
        }

        // insert product table
        for (int i = 1; i <= 5; i++) {
            ContentValues productValues = new ContentValues();
            productValues.put(T2COL2, "ProductName" + i);
            productValues.put(T2COL3, "Description" + i);
            productValues.put(T2COL4, i*10);
            productValues.put(T2COL5, "700 Royal Ave, New Westminster, BC");
            productValues.put(T2COL6, "Category" + i);
            productValues.put(T2COL7, i % 2 == 0 ? "Sell" : "Share");
            productValues.put(T2COL8, "/data/data/com.example.g1_csis3175_002/app_Images/" + i + ".jpg");
            db.insert(TABLE2_NAME, null, productValues);
        }
    }
}
