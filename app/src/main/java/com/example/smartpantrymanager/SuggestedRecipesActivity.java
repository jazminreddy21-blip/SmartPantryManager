package com.example.smartpantrymanager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;

    private ArrayList<Recipe> suggestedRecipes;

    private DatabaseHelper dbHelper;

    private TextView noSuggestionsMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suggested_recipes);

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

        Button backButton =
                findViewById(R.id.btnBackToPantry);

        backButton.setOnClickListener(v -> finish());

        recyclerView =
                findViewById(R.id.rvSuggestedRecipes);

        noSuggestionsMessage =
                findViewById(R.id.tvNoSuggestions);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        suggestedRecipes = new ArrayList<>();

        adapter = new RecipeAdapter(suggestedRecipes);

        recyclerView.setAdapter(adapter);

        dbHelper = new DatabaseHelper(this);

        loadSuggestedRecipes();
    }

    private void loadSuggestedRecipes() {

        Cursor cursor = dbHelper.getAllRecipes();

        suggestedRecipes.clear();

        while (cursor.moveToNext()) {

            int recipeId =
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow("id")
                    );

            String name =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "recipe_name"
                            )
                    );

            String instructions =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "instructions"
                            )
                    );

            String prepTime =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "prep_time"
                            )
                    );

            if (dbHelper.canMakeRecipe(recipeId)) {

                Recipe recipe = new Recipe(
                        recipeId,
                        name,
                        instructions,
                        prepTime
                );

                suggestedRecipes.add(recipe);
            }
        }

        cursor.close();

        adapter.notifyDataSetChanged();

        if (suggestedRecipes.isEmpty()) {

            noSuggestionsMessage.setVisibility(
                    TextView.VISIBLE
            );

        } else {

            noSuggestionsMessage.setVisibility(
                    TextView.GONE
            );
        }
    }
}