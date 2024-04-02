package com.example.g1_csis3175_002;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SellingItemLVAdapter extends ArrayAdapter<ProductModel> {
    private ArrayList<ProductModel> productList;
    private final DatabaseHelper db;
    public SellingItemLVAdapter(@NonNull Context context, ArrayList<ProductModel> objList) {
        super(context, R.layout.listview_layout_seller_items, objList);
        this.db = new DatabaseHelper(getContext());
        this.productList = objList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.listview_layout_seller_items, parent, false);
        ProductModel pm = getItem(position);
        ImageView imageView = customView.findViewById(R.id.imgSellingItem);
        TextView tvProductName = customView.findViewById(R.id.tvListViewProductName);
        TextView tvProductPrice = customView.findViewById(R.id.tvListviewPrice);
        Button btnEdit = customView.findViewById(R.id.btnEditListing);
        Button btnCancel = customView.findViewById(R.id.btnCancelListing);

        Glide.with(getContext())
                .load(pm.getImagePath())
                .into(imageView);
        tvProductName.setText(pm.getProductName());
        tvProductPrice.setText(String.valueOf(pm.getPrice()));
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListingItemActivity.class);
                ProductModel product = db.getProduct(pm.getProductID());
                intent.putExtra("product", product);
                intent.putExtra("editProduct", true);
                getContext().startActivity(intent);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteProduct(pm.getProductID());
                productList.remove(position);
                notifyDataSetChanged();
            }
        });
        return customView;
    }
}
