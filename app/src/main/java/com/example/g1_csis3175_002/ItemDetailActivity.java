package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ItemDetailActivity extends AppCompatActivity {
    private ProductModel product;
    private Data.Builder notificationMessage;
    DatabaseHelper databaseHelper;
    private List<ProductModel> cartItemList;
    EditText edDeliveryAd;
    RadioButton rdbtnPickup;
    RadioButton rdbtnDelivery;
    int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        TextView tvPName = findViewById(R.id.txtProductName);
        TextView tvDiscount = findViewById(R.id.txtProductPrice);
        TextView txtSellerInfo = findViewById(R.id.txtSellerInfo);
        TextView txtUserShipAd = findViewById(R.id.txtPickupAd);
        TextView txtPickupTitle = findViewById(R.id.txtPickupAddress);
        TextView tvDes = findViewById(R.id.txtDescription);
        edDeliveryAd = findViewById(R.id.edDeliveryAd);
        ImageView img = findViewById(R.id.imgProductItemDetail);
        RadioGroup rdbtnGroup = findViewById(R.id.rdbtnGroup);
        rdbtnPickup = findViewById(R.id.rdbtnPickup);
        rdbtnDelivery = findViewById(R.id.rdbtnDelivery);

        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        cartItemList = new ArrayList<>();

        int productID = getIntent().getIntExtra("ProductID", -1);
        Log.d("ProductDetail", "Product ID received: " + productID);

        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(this::onClickBack);

        databaseHelper = new DatabaseHelper(this);
        product = databaseHelper.getProduct(productID);


        tvPName.setText(product.getProductName());
        tvDiscount.setText(String.format(Locale.getDefault(), "$%.2f", product.getPrice()));
        tvDes.setText(product.getDescription());
        txtSellerInfo.setText(product.getSeller());
        txtUserShipAd.setText(product.getPickupAddress());

        rdbtnGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdbtnPickup) {
                    txtUserShipAd.setVisibility(View.VISIBLE);
                    txtPickupTitle.setVisibility(View.VISIBLE);
                    txtUserShipAd.setVisibility(View.VISIBLE);
                    edDeliveryAd.setVisibility(View.GONE);
                    Log.d("ItemDetailActivity", "ProductId shippingAddress: " + product.getPickupAddress());
                } else if (checkedId == R.id.rdbtnDelivery) {

                    txtUserShipAd.setVisibility(View.GONE);
                    txtPickupTitle.setVisibility(View.GONE);
                    edDeliveryAd.setVisibility(View.VISIBLE);
                    Log.d("ProductModel", "ProductId shippingAddress: " + product.getDeliveryAddress());
                }
            }
        });

        Glide.with(this)
                .load(product.getImagePath())
                .into(img);

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = product.getProductName();
                String orderDate = generateRandomOrderDate();
                String status = generateRandomOrderStatus();
                String imagePath = product.getImagePath();

                int orderId = generateRandomOrderId();
                addToCart(product, orderId);

                // Call the insertOrder method
                boolean isSuccess = databaseHelper.insertOrder(productName, orderDate, status, imagePath);

                if (isSuccess) {
                    //Insertion successful
                    Toast.makeText(ItemDetailActivity.this, "Order placed successfully", Toast.LENGTH_SHORT).show();
                    //set up notification title and message
                    notificationMessage = new Data.Builder();
                    if(rdbtnPickup.isChecked()){
                        notificationMessage.putString("title", "Your order is ready for pick up");
                        notificationMessage.putString("content", String.format("%s is ready for pick up at %s", productName, product.getPickupAddress()));
                    }else{
                        notificationMessage.putString("title", "Your order is ready to ship");
                        notificationMessage.putString("content", String.format("%s is ready to ship", productName));
                    }
                    Data data = notificationMessage.build();

                    //Send notification after 5 seconds
                    OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(NotificationHelper.class)
                            .setInputData(data)
                            .setInitialDelay(5, TimeUnit.SECONDS)
                            .build();
                    WorkManager.getInstance(ItemDetailActivity.this).enqueue(notificationWork);
                    startActivity(new Intent(ItemDetailActivity.this, BuyingActivity.class));
                } else {
                    // Error occurred while inserting
                    Toast.makeText(ItemDetailActivity.this, "Failed to placed order", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static int generateRandomOrderId() {
        Random random = new Random();

        int orderId = random.nextInt(9000) + 1000;

        return orderId;
    }

    private void addToCart(ProductModel product, int orderId) {


        if (databaseHelper == null) {
            Toast.makeText(ItemDetailActivity.this, "Null Item.", Toast.LENGTH_LONG).show();
            return;
        }

        // Get the product ID and other necessary details
        int productId = getIntent().getIntExtra("ProductID", -1);
        product.setProductID(productId);


        Log.d("ItemDetailActivity", "ProductId addToCart: " + productId);

        String orderDate = product.getCurrentDate();
        String orderStatus = "Cart";
//        int orderId = generateRandomOrderId();
        String addressToUse;
        if (rdbtnPickup.isChecked()) {
            addressToUse = product.getPickupAddress();
        } else {
            String deliveryAddress = edDeliveryAd.getText().toString();
            addressToUse = deliveryAddress;
        }

        boolean success = databaseHelper.addOrder(orderId, addressToUse, orderDate,
                orderStatus, productId);
        if (success) {
            Toast.makeText(ItemDetailActivity.this, "Item added to cart.", Toast.LENGTH_LONG).show();
            // Show a success message or update UI accordingly

            Intent intent = new Intent(ItemDetailActivity.this, Cart_Activity.class);
            intent.putExtra("ProductID", productId);
            startActivity(intent);

            cartItemList.add(product);
        } else {
            Toast.makeText(ItemDetailActivity.this, "Failed to add item to cart.", Toast.LENGTH_LONG).show();
            // Show an error message or handle the failure scenario
        }

        Intent intent = new Intent(ItemDetailActivity.this, Cart_Activity.class);
        intent.putExtra("OrderID", orderId);
        startActivity(intent);
    }

    // Method to generate a random order date
    public static String generateRandomOrderDate() {
        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Get the current date
        Date currentDate = new Date();

        // Generate a random number of days to subtract from the current date (between 1 and 30)
        Random random = new Random();
        int randomDays = random.nextInt(30) + 1;

        // Subtract the random number of days from the current date
        long millis = currentDate.getTime() - (randomDays * 24L * 3600 * 1000);

        // Create a new Date object with the random date
        Date randomDate = new Date(millis);

        // Format the random date as a string
        return dateFormat.format(randomDate);
    }

    // Method to generate a random order status
    public static String generateRandomOrderStatus() {
        // Define an array of possible order statuses
        String[] statuses = {"Processing", "Shipping", "Delivered"};

        // Generate a random index to select a status from the array
        Random random = new Random();
        int randomIndex = random.nextInt(statuses.length);

        // Return the randomly selected order status
        return statuses[randomIndex];
    }

    public void onClickBack(View view){

        startActivity(new Intent(ItemDetailActivity.this, BuyingActivity.class));
    }
}