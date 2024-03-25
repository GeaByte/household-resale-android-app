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

import java.util.ArrayList;
import java.util.Locale;
import com.bumptech.glide.Glide;

public class ProductGVAdapter extends ArrayAdapter<ProductModel> {
    public ProductGVAdapter(@NonNull Context context, ArrayList<ProductModel> productModelArrayList) {
        super(context, 0, productModelArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listitemView = convertView;
        if (listitemView == null){
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.listview_layout_buying, parent, false);
        }
        ProductModel productModel = getItem(position);
        TextView productName = listitemView.findViewById(R.id.tvProductName);
        TextView productPrice = listitemView.findViewById(R.id.tvPrice);
        ImageView productImage = listitemView.findViewById(R.id.imgProduct);
        productName.setText(productModel.getProductName());
        productPrice.setText(String.format(Locale.US, "$%.2f", productModel.getPrice()));

        Glide.with(getContext())
                .load(productModel.getImagePath())
                .into(productImage);


        return listitemView;
    }
}
