package com.example.g1_csis3175_002;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class OrderDetailLVAdapter extends ArrayAdapter<OrderModel> {
    public OrderDetailLVAdapter(@NonNull Context context, ArrayList<OrderModel> orderModelArrayList) {
        super(context, 0, orderModelArrayList);
    }

    public View getView(int position, View convertView, ViewGroup parent){
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
    }
}
