package com.example.g1_csis3175_002;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ListItemSeller extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imgView;

    private EditText edtItemtitle, editTextDescription, editTextPrice, editTextLocation;
    private Spinner spinnerCategory;
    private RadioGroup radioGroupSellRent;
    private RadioButton radioButtonSell, radioButtonRent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_item_seller);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtItemtitle = findViewById(R.id.edtItemtitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextLocation = findViewById(R.id.editTextLocation);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        radioGroupSellRent = findViewById(R.id.radioGroupSellRent);
        radioButtonSell = findViewById(R.id.radioButtonSell);
        radioButtonRent = findViewById(R.id.radioButtonRent);
        imgView = findViewById(R.id.imgView);
        Button btnAddImage = findViewById(R.id.btnAddImage);
        Button btnList = findViewById(R.id.btnList);


        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // Get data from views
                    String title = edtItemtitle.getText().toString();
                    String description = editTextDescription.getText().toString();
                    String price = editTextPrice.getText().toString();
                    String location = editTextLocation.getText().toString();
                    String category = spinnerCategory.getSelectedItem().toString();
                    String sellRent = radioButtonSell.isChecked() ? "Sell" : "Rent";
                // Save the image to the filesystem
                String imagePath = saveImageToInternalStorage(imgView);

                // Insert data into the database
                DatabaseHelperSeller dbHelper = new DatabaseHelperSeller(ListItemSeller.this);
                boolean inserted = dbHelper.insertItem(title, description, price, location, category, sellRent, imagePath);
                if (inserted) {
                    Toast.makeText(ListItemSeller.this, "Item listed successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListItemSeller.this, "Failed to list item", Toast.LENGTH_SHORT).show();
                }



            }
        });

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imgView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String saveImageToInternalStorage(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawable) {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        } else {
            throw new IllegalArgumentException("Unsupported drawable type");
        }

        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
        File file = wrapper.getDir("Images", MODE_PRIVATE);
        file = new File(file, "image" + System.currentTimeMillis() + ".jpg");

        try {
            OutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }
}