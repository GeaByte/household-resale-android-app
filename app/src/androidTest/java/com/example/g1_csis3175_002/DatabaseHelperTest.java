package com.example.g1_csis3175_002;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;

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
    public void addRecordTest() {
        boolean result = dbHelper.addUser("John", "Doe", "BC", "V65B65", "Vancouver", 555, "xxxamfa@gmail.com", "password");
        assertTrue(result);
    }

}