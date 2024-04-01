package com.example.g1_csis3175_002;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class OrderDetailLVAdapter extends ArrayAdapter<OrderModel> {

    public OrderDetailLVAdapter(Context context, ArrayList<OrderModel> orders) {
        super(context, 0, orders);

       /* for (OrderModel order : orders) {
            order.setDate(generateRandomDate());
            order.setStatus(calculateStatus(order.getDate()));
        }*/
    }





  /*  public OrderDetailLVAdapter(@NonNull Context context, ArrayList<OrderModel> orderModelArrayList) {
        super(context, 0, orderModelArrayList);

        for (OrderModel order : orders) {
            order.setDate(generateRandomDate());
            order.setStatus(calculateStatus(order.getDate()));
        }


    }*/

 /*   public View getView(int position, View convertView, ViewGroup parent){
        View listitemView = convertView;


        if (listitemView == null){
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.listview_layout_order_history, parent, false);
        }
        OrderModel orderModel = getItem(position);
        TextView orderId = listitemView.findViewById(R.id.tvShowOrderID);
        TextView itemName = listitemView.findViewById(R.id.tvShowItemName);
        TextView orderDate = listitemView.findViewById(R.id.tvShowOrderDate);
        TextView status = listitemView.findViewById(R.id.tvShowStatus);


        orderId.setText(Long.toString(orderModel.getId()));
        int itemID = orderModel.getItemID();
//        String items = String.join(", ", itemID);
//        itemName.setText(items);
        itemName.setText(itemID);
        orderDate.setText(orderModel.getDate());
        status.setText(orderModel.getStatus());
        return listitemView;
    }*/

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
        TextView textOrderStatus = convertView.findViewById(R.id.tvShowStatus);
        ImageView img = convertView.findViewById(R.id.imgItem);




        // Populate the data into the template view using the data object
        if (order != null) {
            textOrderID.setText(" "+order.getId());
            textShippingAddress.setText(" " + order.getShippingAddress());
            textOrderDate.setText(" "+ order.getDate());
            textOrderStatus.setText(" "+order.getStatus());
           /* setDate(textOrderDate, order);
            setStatus(textOrderStatus, order);*/

            // Load image using Glide
            Glide.with(getContext())
                    .load(order.getProductImagePath()) // Assuming getOrder method returns the OrderModel object
                    .into(img);




        }

        // Return the completed view to render on screen
        return convertView;
    }


    private void setDate(TextView textView, OrderModel order) {
        textView.setText(" "+order.getDate());
    }

    // Set status for the order
    private void setStatus(TextView textView, OrderModel order) {
        textView.setText(" "+order.getStatus());
    }

    // Generate a random date that is more than 15 days ago
    private String generateRandomDate() {
        Calendar calendar = Calendar.getInstance();
        Random random = new Random();
        int daysAgo = random.nextInt(15) + 16; // Generate a number between 16 and 30
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }

    // Calculate the status based on the order date
    private String calculateStatus(String orderDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date date = dateFormat.parse(orderDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, 15); // Add 10 days to the order date
            Date shippingDate = calendar.getTime();
            calendar.add(Calendar.DAY_OF_YEAR, 10); // Add 5 more days for delivery
            Date deliveryDate = calendar.getTime();
            Date currentDate = new Date();
            if (currentDate.before(shippingDate)) {
                return "Processing";
            } else if (currentDate.before(deliveryDate)) {
                return "Shipping";
            } else {
                return "Delivered";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



}
