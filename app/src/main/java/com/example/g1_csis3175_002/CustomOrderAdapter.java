package com.example.g1_csis3175_002;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomOrderAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<OrderModel> mOrders;

    public CustomOrderAdapter(Context context, ArrayList<OrderModel> orders) {
        mContext = context;
        mOrders = orders;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_order, parent, false);
        }

        TextView productNameTextView = convertView.findViewById(R.id.tvShowOrderId);
        TextView orderDateTextView = convertView.findViewById(R.id.txtShowAddress);
        TextView orderStatusTextView = convertView.findViewById(R.id.tvShowOrderStatus);

        // Get the OrderModel object for this position
        OrderModel order = mOrders.get(position);

        // Set the text for the TextViews
        productNameTextView.setText(order.getItemNames());
        orderDateTextView.setText(order.getDate());
        orderStatusTextView.setText(order.getStatus());

        // Set the tag of the root view to the corresponding OrderModel object
        convertView.setTag(order);

        return convertView;

    }
}
