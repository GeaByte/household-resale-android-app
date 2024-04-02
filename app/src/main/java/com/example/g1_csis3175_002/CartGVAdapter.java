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

    public CartGVAdapter(@NonNull Context context, ArrayList<ProductModel> productModelArrayList,
                         DatabaseHelper dbHelper) {
        super(context, 0, productModelArrayList);
        this.databaseHelper = dbHelper;
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
        int productId = databaseHelper.getProductIDFromOrder(orderId);

        Glide.with(getContext())
                .load(cartItemList.getImagePath())
                .into(imagePath);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });


        return listitemView;
    }


    // Remove item from cart
    private void removeItem(int position) {
        ProductModel itemToRemove = getItem(position);
        int productId = itemToRemove.getProductID();
        Log.d("CartGVAdapter", "Removing item: " + itemToRemove.getProductName() + ", ID: " + itemToRemove.getProductID());
        if (itemToRemove != null) {
            // Remove do banco de dados
            if (databaseHelper.deleteCartItem(itemToRemove.getProductID())) {
                // Remove da lista exibida
                remove(itemToRemove);
                notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "Falha ao remover o item.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
