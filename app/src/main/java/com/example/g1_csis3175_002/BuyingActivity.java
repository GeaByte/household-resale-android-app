package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying);

        TextView title = findViewById(R.id.tvBuyTitle);

        databaseHelper = new DatabaseHelper(this);
        locationHelper = new LocationHelper(this);

        //sending instant reminder notification
//        NotificationHelper.showNotification(this, "Pick-up Reminder", "testing");
        Spinner spSort = findViewById(R.id.spSort);
        productGV = findViewById(R.id.GVbuying);
        List<ProductModel> productModelList;
        productModelList = databaseHelper.getAllProducts();
        adapter = new ProductGVAdapter(BuyingActivity.this, productModelList);
        productGV.setAdapter(adapter);
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<ProductModel> filteredList = databaseHelper.searchProducts(newText);
                adapter.clear();
                adapter.addAll(filteredList);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
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
                            pm.setCoordinates(locationHelper.convertToGeo(pm.getPickupAddress()));
                            pm.setDistance(pm.calculateDistance(locationHelper, latitude, longitude, pm.getCoordinates().get(0), pm.getCoordinates().get(1)));
                        }
                        Collections.sort(productModelList, Collections.reverseOrder());
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
}