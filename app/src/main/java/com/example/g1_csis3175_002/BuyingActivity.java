package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BuyingActivity extends AppCompatActivity implements LocationHelper.LocationCallbackListener {
    GridView productGV;
    DatabaseHelper databaseHelper;
    ProductGVAdapter adapter;
    private double longitude;
    private double latitude;
    private LocationHelper locationHelper;
    private List<ProductModel> productModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying);

        databaseHelper = new DatabaseHelper(this);
        locationHelper = new LocationHelper(this);

        Spinner spSort = findViewById(R.id.spSort);
        productGV = findViewById(R.id.GVbuying);
        productModelList = databaseHelper.getAllProducts();
        adapter = new ProductGVAdapter(BuyingActivity.this, productModelList);
        productGV.setAdapter(adapter);
        SearchView searchView = findViewById(R.id.searchView);
        Button btnBack = findViewById(R.id.btnBuyingBack);
        btnBack.setOnClickListener(this::onClickBack);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //is called when user submits the query (press enter)
            @Override
            public boolean onQueryTextSubmit(String query) {
                productModelList.clear();
                productModelList = databaseHelper.searchProducts(query);
                adapter.clear();
                adapter.addAll(productModelList);
                productGV.setAdapter(adapter);
                return true;
            }
            //is called when the query text change
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //get spinner selection for sorting
        spSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position){
                    //sort by most recent
                    case 0:
                        //for api 21
                        Collections.sort(productModelList, new Comparator<ProductModel>() {
                            @Override
                            public int compare(ProductModel p1, ProductModel p2) {
                                return p2.getUploadTime().compareTo(p1.getUploadTime());
                            }
                        });
                        adapter = new ProductGVAdapter(BuyingActivity.this, productModelList);
                        productGV.setAdapter(adapter);
                        break;
                    //sort by price from low to high
                    case 1:
                        //for api 21
                        Collections.sort(productModelList, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
                        adapter = new ProductGVAdapter(BuyingActivity.this, productModelList);
                        productGV.setAdapter(adapter);
                        break;
                    //sort by distance from close to away
                    case 2:
                        for (ProductModel pm : productModelList){
                            pm.setDistance(ProductModel.calculateDistance(latitude, longitude, pm.getLatitude(), pm.getLongitude()));
                        }
                        Collections.sort(productModelList);
                        adapter = new ProductGVAdapter(BuyingActivity.this, productModelList);
                        productGV.setAdapter(adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onClickSelect(View view){
        int productID = (int) view.getTag();
        Intent detailIntent = new Intent(BuyingActivity.this, ItemDetailActivity.class);
        detailIntent.putExtra("ProductID", productID);
        startActivity(detailIntent);
    }

    //request user's current location
    @Override
    protected void onStart(){
        super.onStart();
        locationHelper.requestLocationUpdates(this);
    }

    //if get user's location
    @Override
    public void onLocationAvailable(Location location) {
        if (location != null){
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    public void onClickBack(View view){
        startActivity(new Intent(BuyingActivity.this, HomeActivity.class));
    }
}