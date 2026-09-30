package com.example.smartpantrymanager;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private List<PantryItem> pantryItems;
    private Runnable onPantryChanged;

    public PantryAdapter(List<PantryItem> pantryItems, Runnable onPantryChanged) {
        this.pantryItems = pantryItems;
        this.onPantryChanged = onPantryChanged;
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pantry, parent, false);

        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull PantryViewHolder holder,
            int position
    ) {

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

        // Edit button
        holder.editButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    v.getContext(),
                    EditIngredientActivity.class
            );

            intent.putExtra("ingredient_id", item.getId());

            v.getContext().startActivity(intent);
        });

        // Delete button
        holder.deleteButton.setOnClickListener(v -> {

            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Ingredient")
                    .setMessage(
                            "Are you sure you want to delete "
                                    + item.getName() + "?"
                    )
                    .setPositiveButton("Delete", (dialog, which) -> {

                        DatabaseHelper dbHelper =
                                new DatabaseHelper(v.getContext());

                        boolean success =
                                dbHelper.deleteIngredient(item.getId());

                        if (success) {

                            int adapterPosition =
                                    holder.getAdapterPosition();

                            if (adapterPosition != RecyclerView.NO_POSITION) {

                                pantryItems.remove(adapterPosition);

                                notifyItemRemoved(adapterPosition);

                                if (onPantryChanged != null) {
                                    onPantryChanged.run();
                                }
                            }
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return pantryItems.size();
    }

    public static class PantryViewHolder
            extends RecyclerView.ViewHolder {

        TextView ingredientName;
        TextView ingredientQuantity;
        TextView ingredientExpiry;

        Button editButton;
        Button deleteButton;

        public PantryViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientName =
                    itemView.findViewById(R.id.tvIngredientName);

            ingredientQuantity =
                    itemView.findViewById(R.id.tvIngredientQuantity);

            ingredientExpiry =
                    itemView.findViewById(R.id.tvIngredientExpiry);

            editButton =
                    itemView.findViewById(R.id.btnEditIngredient);

            deleteButton =
                    itemView.findViewById(R.id.btnDeleteIngredient);
        }
    }
}