package com.example.g1_csis3175_002;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.database.Cursor;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

public class DatabaseHelperTest {

    private DatabaseHelper dbHelper;
    private Context context;

    @Before
    public void setUp() {
        // get context
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbHelper = new DatabaseHelper(context);
    }

    @Test
    public void useAppContext() {
        // test get context
        assertEquals("com.example.g1_csis3175_002", context.getPackageName());
    }
    @Test
    public void addUserTest() {
        boolean result = dbHelper.addUser("John", "Doe", "BC", "V78N78", "Vancouver", 123, "xxxamfa@gmail.com", "password123", false, false);
        assertTrue(result);
    }

    @Test
    public void getUserTest() {
        dbHelper.addUser("Jane", "Doe", "124 Elm Street", "A1B2C3", "Springfield", 5555678, "jane@example.com", "password");
        Cursor cursor = dbHelper.getUser("Jane");
        assertTrue(cursor.moveToFirst()); // True if there is data (the user exists)
        cursor.close();
    }

    @Test
    public void updateUserTest() {
        dbHelper.addUser("John", "Doe", "123 Elm Street", "A1B2C3", "Springfield", 5551234, "john@example.com", "password");
        boolean result = dbHelper.updateUser("John", "123", "125 Elm Street", "A1B2C4", "Springfield", 222, "johnny@example.com", "new_password");
        assertTrue(result);
    }

    @Test
    public void deleteUserTest() {
        dbHelper.addUser("John", "Doe", "123 Elm Street", "A1B2C3", "Springfield", 5551234, "john@example.com", "password");
        boolean result = dbHelper.deleteUser("John");
        assertTrue(result);
    }

    @Test
    public void addProductTest() {
        long productId = dbHelper.addProduct("nice", "123", "Nice", 1, 1, 123);
        assertNotEquals(-1, productId);
    }


    @Test
    public void getProductTest() {
        dbHelper.addProduct("nice", "123", "Nice", 1, 1, 123);
        Cursor cursor = dbHelper.getProduct(1);
        assertTrue(cursor.moveToFirst()); // True if there is data (the user exists)
        cursor.close();
    }

    @Test
    public void updateProductTest() {
        // Add product and get the new ID
        long newProductId = dbHelper.addProduct("nice", "123", "Nice", 1, 1, 123.0);

        // Check if product was added successfully
        assertTrue(newProductId != -1);

        // Update the product using the new ID
        boolean updateResult = dbHelper.updateProduct((int)newProductId, "Doe", "125 Elm Street", "NewPicture.jpg", 1, 1, 123.0);
        assertTrue(updateResult);
    }

    @Test
    public void deleteProductTest() {
        dbHelper.addProduct("nice", "123", "Nice", 1, 1, 123);
        boolean result = dbHelper.deleteProduct(1);
        assertTrue(result);
    }






}