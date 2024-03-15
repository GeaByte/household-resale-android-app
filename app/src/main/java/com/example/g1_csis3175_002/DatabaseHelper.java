package com.example.g1_csis3175_002;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "Information.db";
    final static String TABLE1_NAME = "User";
    final static String T1COL1 = "Username";
    final static String T1COL2 = "Name";
    final static String T1COL3 = "Address";
    final static String T1COL4 = "Contact";
    final static String T1COL5 = "Email";
    final static String T1COL6 = "Password";
    final static int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE1_NAME +
                "(" + T1COL1 + " TEXT PRIMARY KEY," +
                T1COL2 + " TEXT," +
                T1COL3 + " TEXT," +
                T1COL4 + " INTEGER," +
                T1COL5 + " TEXT," +
                T1COL6 + " TEXT)";

        sqLiteDatabase.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addRecord(String name, String username, String address, int contact,
                             String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL1,username);
        values.put(T1COL2,name);
        values.put(T1COL3,address);
        values.put(T1COL4, contact);
        values.put(T1COL5, email);
        values.put(T1COL6, password);

        long r = sqLiteDatabase.insert(TABLE1_NAME, null, values);
        if(r>0)
            return true;
        else
            return false;

    }


}
