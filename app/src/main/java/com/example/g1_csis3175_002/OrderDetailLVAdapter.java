package com.example.g1_csis3175_002;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class OrderDetailLVAdapter extends ArrayAdapter<OrderModel>{



    // Constructor
    public OrderDetailLVAdapter(Context context, ArrayList<OrderModel> orders) {
        super(context, 0, orders);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        OrderModel order = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_layout_order_history, parent, false);
        }

        // Lookup view for data population
        TextView textOrderID = convertView.findViewById(R.id.tvShowOrderID);
        TextView textShippingAddress = convertView.findViewById(R.id.tvShowItemName);
        TextView textOrderDate = convertView.findViewById(R.id.tvShowOrderDate);
        TextView textOrderStatus = convertView.findViewById(R.id.tvShowOrderDate);




        // Populate the data into the template view using the data object
        if (order != null) {
            textOrderID.setText("Order ID: " + order.getId());
            textShippingAddress.setText("Shipping Address: " + order.getItemNames());
            textOrderDate.setText("Order Date: " + order.getDate());
            textOrderStatus.setText("Order Status: " + order.getStatus());
        }

        // Return the completed view to render on screen
        return convertView;
    }


}



