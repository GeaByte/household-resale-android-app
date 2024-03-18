package com.example.g1_csis3175_002;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


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
    final static String T2COL2 = "Price";
    final static String T2COL3 = "Description";
    final static String T2COL4 = "SellersInfo";
    final static String T2COL5 = "Picture";
    final static String T2COL6 = "OrderID";
    final static String T2COL7 = "Quantity";
    final static String TABLE3_NAME = "UserOrder";
    final static String T3COL1 = "OrderID";
    final static String T3COL2 = "ShippingAddress";
    final static String T3COL3 = "OrderDate";
    final static String T3COL4 = "OrderStatus";
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
                T2COL2 + " REAL," +
                T2COL3 + " TEXT," +
                T2COL4 + " TEXT," +
                T2COL5 + " TEXT," +
                T2COL6 + " INTEGER," +
                T2COL7 + " INTEGER," +
                "FOREIGN KEY(" + T2COL6 + ") REFERENCES " + TABLE3_NAME + "(" +
                T3COL1 + ")" + ");";


        String queryTable3 = "CREATE TABLE " + TABLE3_NAME + "(" +
                T3COL1 + " INTEGER PRIMARY KEY," +
                T3COL2 + " TEXT," +
                T3COL3 + " TEXT," +
                T3COL4 + " TEXT)";

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
        values.put(T1COL9, isBuyer ? 1 : 0);
        values.put(T1COL10, isSeller ? 1 : 0);


        long r = sqLiteDatabase.insert(TABLE1_NAME, null, values);
        return r > 0;

    }
    public boolean addUser(String username, String name, String address, String zipcode, String city,
                           int contact, String email, String password){
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

        long r = sqLiteDatabase.insert(TABLE1_NAME, null, values);
        return r > 0;

    }

    // getUser
    public Cursor getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE1_NAME, null, T1COL1 + "=?", new String[] { username }, null, null, null);
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



    //2-Product
    // addProduct
    public long addProduct(String description, String sellersInfo, String picture, int orderId, int quantity, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL2, price);
        values.put(T2COL3, description);
        values.put(T2COL4, sellersInfo);
        values.put(T2COL5, picture);
        values.put(T2COL6, orderId);
        values.put(T2COL7, quantity);

        return db.insert(TABLE2_NAME, null, values);
    }

    // getProduct
    public Cursor getProduct(int productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE2_NAME, null, T2COL1 + "=?", new String[] {String.valueOf(productId)}, null, null, null);
    }

    // updateProduct
    public boolean updateProduct(int productId, String description, String sellersInfo, String picture, int orderId, int quantity, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL2, price);
        values.put(T2COL3, description);
        values.put(T2COL4, sellersInfo);
        values.put(T2COL5, picture);
        values.put(T2COL6, orderId);
        values.put(T2COL7, quantity);

        int rowsAffected = db.update(TABLE2_NAME, values, T2COL1 + "=?", new String[] {String.valueOf(productId)});
        return rowsAffected > 0;
    }

    // deleteProduct
    public boolean deleteProduct(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE2_NAME, T2COL1 + "=?", new String[] {String.valueOf(productId)});
        return rowsDeleted > 0;
    }

    public boolean addOrder(Integer oId, String address, String date, String status){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL2,oId);
        values.put(T2COL3,address);
        values.put(T2COL4, date);
        values.put(T2COL5, status);

        long r = sqLiteDatabase.insert(TABLE2_NAME, null, values);
        return r > 0;

    }
}
