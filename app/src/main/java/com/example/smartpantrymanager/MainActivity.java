package com.example.smartpantrymanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseHelper dbHelper;
    private PantryAdapter adapter;
    private List<PantryItem> pantryItems;

    private TextView emptyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

// Recipe seeding temporarily disabled while testing startup
// RecipeSeeder.seedRecipes(dbHelper);

        // Add Ingredient button
        Button addButton = findViewById(R.id.btnAddIngredient);

        addButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    AddIngredientActivity.class
            );

            startActivity(intent);
        });

        // View Recipes button
        Button viewRecipesButton =
                findViewById(R.id.btnViewRecipes);

        viewRecipesButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    RecipesActivity.class
            );

            startActivity(intent);
        });

        // Suggested Recipes button
        Button suggestedRecipesButton =
                findViewById(R.id.btnSuggestedRecipes);

        suggestedRecipesButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    SuggestedRecipesActivity.class
            );



            startActivity(intent);
        });

        // Settings button
        Button settingsButton =
                findViewById(R.id.btnSettings);

        settingsButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    SettingsActivity.class
            );

            startActivity(intent);
        });

        // Pantry RecyclerView
        recyclerView = findViewById(R.id.recyclerPantry);

        emptyMessage = findViewById(R.id.tvEmptyPantry);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (dbHelper != null) {
            loadPantryItems();
        }
    }

    private void loadPantryItems() {

        pantryItems = new ArrayList<>();

        Cursor cursor = dbHelper.getAllIngredients();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id")
            );

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow("name")
            );

            double quantity = cursor.getDouble(
                    cursor.getColumnIndexOrThrow("quantity")
            );

            String unit = cursor.getString(
                    cursor.getColumnIndexOrThrow("unit")
            );

            String expiryDate = cursor.getString(
                    cursor.getColumnIndexOrThrow("expiry_date")
            );

            PantryItem item = new PantryItem(
                    id,
                    name,
                    quantity,
                    unit,
                    expiryDate
            );

            pantryItems.add(item);
        }

        cursor.close();

        adapter = new PantryAdapter(
                pantryItems,
                () -> {

                    if (pantryItems.isEmpty()) {

                        emptyMessage.setVisibility(
                                View.VISIBLE
                        );

                    } else {

                        emptyMessage.setVisibility(
                                View.GONE
                        );
                    }
                }
        );

        recyclerView.setAdapter(adapter);

        if (pantryItems.isEmpty()) {

            emptyMessage.setVisibility(
                    View.VISIBLE
            );

        } else {

            emptyMessage.setVisibility(
                    View.GONE
            );
        }
    }
}