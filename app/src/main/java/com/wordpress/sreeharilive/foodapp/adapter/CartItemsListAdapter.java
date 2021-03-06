package com.wordpress.sreeharilive.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wordpress.sreeharilive.foodapp.R;
import com.wordpress.sreeharilive.foodapp.activity.FoodDescriptionActivity;
import com.wordpress.sreeharilive.foodapp.model.FoodItem;
import com.wordpress.sreeharilive.foodapp.util.Constants;

import java.util.ArrayList;

/***
 * Created by negibabu on 3/18/17.
 */

public class CartItemsListAdapter extends RecyclerView.Adapter<CartItemsListAdapter.ViewHolder>{

    private Context context;

    private ArrayList<FoodItem> foodItems;

    public CartItemsListAdapter(Context context, ArrayList<FoodItem> foodItems) {
        this.context = context;
        this.foodItems = foodItems;
    }

    @Override
    public CartItemsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.cart_list_item,parent,false);
        return new CartItemsListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final CartItemsListAdapter.ViewHolder holder, final int position) {


        holder.nameTextView.setText(
                foodItems.get(position).getName()
        );

        holder.priceTextView.setText(
                String.valueOf(foodItems.get(position).getPrice())
        );

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodDescriptionActivity.class);
                intent.putExtra(Constants.FROM_CART_EXTRA_KEY,true);
                intent.putExtra(Constants.SELECTED_ITEM_KEY,foodItems.get(holder.getAdapterPosition()));
                intent.putExtra(Constants.SELECTED_ITEM_INDEX,position);
                context.startActivity(intent);
            }
        });

        holder.qtyTextView.setText(String.valueOf(foodItems.get(position).getQuantity()));

        Picasso.with(context).load(foodItems.get(position).getImageUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView, priceTextView, qtyTextView;
        View rootView;

        ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.foodImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.itemNameTextView);
            priceTextView = (TextView) itemView.findViewById(R.id.priceTextView);
            qtyTextView = (TextView) itemView.findViewById(R.id.qtyTextView);
            rootView = itemView;

        }
    }
}
