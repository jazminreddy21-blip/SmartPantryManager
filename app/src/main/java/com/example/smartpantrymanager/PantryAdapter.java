package com.example.smartpantrymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private List<PantryItem> pantryItems;

    public PantryAdapter(List<PantryItem> pantryItems) {
        this.pantryItems = pantryItems;
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pantry, parent, false);

        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {

        PantryItem item = pantryItems.get(position);

        holder.ingredientName.setText(item.getName());

        holder.ingredientQuantity.setText(
                item.getQuantity() + " " + item.getUnit()
        );

        if (item.getExpiryDate() == null || item.getExpiryDate().isEmpty()) {
            holder.ingredientExpiry.setText("No expiry date");
        } else {
            holder.ingredientExpiry.setText(
                    "Expires: " + item.getExpiryDate()
            );
        }

        holder.editButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    v.getContext(),
                    EditIngredientActivity.class
            );

            intent.putExtra("ingredient_id", item.getId());

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pantryItems.size();
    }

    public static class PantryViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientName;
        TextView ingredientQuantity;
        TextView ingredientExpiry;
        Button editButton;
        public PantryViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientName = itemView.findViewById(R.id.tvIngredientName);
            ingredientQuantity = itemView.findViewById(R.id.tvIngredientQuantity);
            ingredientExpiry = itemView.findViewById(R.id.tvIngredientExpiry);
            editButton = itemView.findViewById(R.id.btnEditIngredient);
        }
    }
}