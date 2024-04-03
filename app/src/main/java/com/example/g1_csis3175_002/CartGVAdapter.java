package com.example.g1_csis3175_002;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;

public class CartGVAdapter extends ArrayAdapter<ProductModel> {

    DatabaseHelper databaseHelper;
    private OnRemoveItemClickListener removeItemClickListener;

    public interface OnRemoveItemClickListener {
        void onRemoveItemClick(int position);
    }
    public CartGVAdapter(@NonNull Context context, ArrayList<ProductModel> productModelArrayList,
                         DatabaseHelper dbHelper) {
        super(context, 0, productModelArrayList);
        this.databaseHelper = dbHelper;
    }

    public void setOnRemoveItemClickListener(OnRemoveItemClickListener listener) {
        this.removeItemClickListener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listitemView = convertView;
        if (listitemView == null){
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.listview_layout_cart, parent, false);
        }
        ProductModel cartItemList = getItem(position);
        TextView productName = listitemView.findViewById(R.id.txtProductCart);
        TextView productPrice = listitemView.findViewById(R.id.txtPriceCart);
        ImageView imagePath = listitemView.findViewById(R.id.imgProductCart);
        Button btnRemove = listitemView.findViewById(R.id.btnRemove);

        productName.setText(cartItemList.getProductName());
        productPrice.setText(String.format(Locale.US, "$%.2f", cartItemList.getPrice()));
        int orderId = cartItemList.getOrderId();

        Glide.with(getContext())
                .load(cartItemList.getImagePath())
                .into(imagePath);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (removeItemClickListener != null) {
                    removeItemClickListener.onRemoveItemClick(position);
                }
            }
        });

        return listitemView;
    }

    // Remove item from cart
    public double removeItem(int position) {
        ProductModel itemToRemove = getItem(position);
        int productId = itemToRemove.getProductID();
        Log.d("CartGVAdapter", "Removing item: " + itemToRemove.getProductName() + ", ID: " + itemToRemove.getProductID());
        if (itemToRemove != null) {
            double itemPrice = itemToRemove.getPrice();
            remove(itemToRemove);
            notifyDataSetChanged();
            return itemPrice;
        }
        return 0.0;
    }
}
