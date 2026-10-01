package com.example.smartpantrymanager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RecipeDetailActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    private TextView recipeName;
    private TextView prepTime;
    private TextView ingredients;
    private TextView instructions;

    private int recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_detail);

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

        recipeName = findViewById(R.id.tvDetailRecipeName);
        prepTime = findViewById(R.id.tvDetailPrepTime);
        ingredients = findViewById(R.id.tvDetailIngredients);
        instructions = findViewById(R.id.tvDetailInstructions);
        Button backButton = findViewById(R.id.btnBack);

        backButton.setOnClickListener(v -> {
            finish();
        });
        dbHelper = new DatabaseHelper(this);

        recipeId = getIntent().getIntExtra("recipe_id", -1);

        loadRecipe();
    }

    private void loadRecipe() {

        Cursor recipeCursor = dbHelper.getAllRecipes();

        while (recipeCursor.moveToNext()) {

            int id = recipeCursor.getInt(
                    recipeCursor.getColumnIndexOrThrow("id")
            );

            if (id == recipeId) {

                String name = recipeCursor.getString(
                        recipeCursor.getColumnIndexOrThrow("recipe_name")
                );

                String time = recipeCursor.getString(
                        recipeCursor.getColumnIndexOrThrow("prep_time")
                );

                String recipeInstructions = recipeCursor.getString(
                        recipeCursor.getColumnIndexOrThrow("instructions")
                );

                recipeName.setText(name);
                prepTime.setText("Prep time: " + time);
                instructions.setText(recipeInstructions);

                break;
            }
        }

        recipeCursor.close();

        loadIngredients();
    }

    private void loadIngredients() {

        Cursor cursor = dbHelper.getRecipeIngredients(recipeId);

        StringBuilder ingredientText = new StringBuilder();

        while (cursor.moveToNext()) {

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow("ingredient_name")
            );

            double quantity = cursor.getDouble(
                    cursor.getColumnIndexOrThrow("ingredient_quantity")
            );

            String unit = cursor.getString(
                    cursor.getColumnIndexOrThrow("ingredient_unit")
            );

            ingredientText
                    .append("• ")
                    .append(quantity)
                    .append(" ")
                    .append(unit)
                    .append(" ")
                    .append(name)
                    .append("\n");
        }

        cursor.close();

        ingredients.setText(ingredientText.toString());
    }
}