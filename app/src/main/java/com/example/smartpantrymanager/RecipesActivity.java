package com.example.smartpantrymanager;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private ArrayList<Recipe> recipes;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipes);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        recyclerView = findViewById(R.id.rvRecipes);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recipes = new ArrayList<>();

        adapter = new RecipeAdapter(recipes);

        recyclerView.setAdapter(adapter);

        dbHelper = new DatabaseHelper(this);

        loadRecipes();
    }

    private void loadRecipes() {

        Cursor cursor = dbHelper.getAllRecipes();

        recipes.clear();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id")
            );

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow("recipe_name")
            );

            String instructions = cursor.getString(
                    cursor.getColumnIndexOrThrow("instructions")
            );

            String prepTime = cursor.getString(
                    cursor.getColumnIndexOrThrow("prep_time")
            );

            Recipe recipe = new Recipe(
                    id,
                    name,
                    instructions,
                    prepTime
            );

            recipes.add(recipe);
        }

        cursor.close();

        adapter.notifyDataSetChanged();
    }
}