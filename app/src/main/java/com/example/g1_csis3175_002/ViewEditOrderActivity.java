package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewEditOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_order);

//        Button view = findViewById(R.id.btnView);
        ListView lv = findViewById(R.id.lvOrders);
        ArrayList<OrderModel> orderModelArrayList = new ArrayList<>();

        String[] sample = {"product A", "product B"};
        orderModelArrayList.add(new OrderModel(001L, sample, "03-16-2024", "delivered"));

        OrderDetailLVAdapter adapter = new OrderDetailLVAdapter(this, orderModelArrayList);
        lv.setAdapter((adapter));

    }

    public void onClickView(View view){
        startActivity(new Intent(ViewEditOrderActivity.this, OrderDetailActivity.class));
    }

    public void onClickEdit(View view){
        //direct to edit order page
    }
}