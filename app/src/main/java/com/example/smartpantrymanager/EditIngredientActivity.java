package com.example.smartpantrymanager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditIngredientActivity extends AppCompatActivity {

    private EditText ingredientName;
    private EditText quantity;
    private EditText expiryDate;
    private Spinner unitSpinner;

    private DatabaseHelper dbHelper;

    private int ingredientId;

    private String[] units = {
            "Unit",
            "grams",
            "kilograms",
            "ml",
            "litres",
            "pieces",
            "cups",
            "tablespoons",
            "teaspoons"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_ingredient);

        ingredientName = findViewById(R.id.etEditIngredientName);
        quantity = findViewById(R.id.etEditQuantity);
        expiryDate = findViewById(R.id.etEditExpiryDate);
        unitSpinner = findViewById(R.id.spEditUnit);

        Button updateButton = findViewById(R.id.btnUpdateIngredient);

        dbHelper = new DatabaseHelper(this);

        // Get the ingredient ID sent from PantryAdapter
        ingredientId = getIntent().getIntExtra("ingredient_id", -1);

        // Set up the unit dropdown
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                units
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        unitSpinner.setAdapter(adapter);

        // Load the existing ingredient
        loadIngredient();

        updateButton.setOnClickListener(v -> updateIngredient());
    }

    private void loadIngredient() {

        Cursor cursor = dbHelper.getIngredientById(ingredientId);

        if (cursor.moveToFirst()) {

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow("name")
            );

            double quantityValue = cursor.getDouble(
                    cursor.getColumnIndexOrThrow("quantity")
            );

            String unit = cursor.getString(
                    cursor.getColumnIndexOrThrow("unit")
            );

            String expiry = cursor.getString(
                    cursor.getColumnIndexOrThrow("expiry_date")
            );

            ingredientName.setText(name);
            quantity.setText(String.valueOf(quantityValue));
            expiryDate.setText(expiry);

            // Select the saved unit in the dropdown
            for (int i = 0; i < units.length; i++) {

                if (units[i].equals(unit)) {
                    unitSpinner.setSelection(i);
                    break;
                }
            }
        }

        cursor.close();
    }

    private void updateIngredient() {

        String name = ingredientName.getText().toString().trim();
        String quantityText = quantity.getText().toString().trim();
        String unit = unitSpinner.getSelectedItem().toString();
        String expiry = expiryDate.getText().toString().trim();

        if (name.isEmpty()) {
            ingredientName.setError("Please enter an ingredient");
            return;
        }

        if (quantityText.isEmpty()) {
            quantity.setError("Please enter the quantity");
            return;
        }

        double quantityValue = Double.parseDouble(quantityText);

        boolean success = dbHelper.updateIngredient(
                ingredientId,
                name,
                quantityValue,
                unit,
                expiry
        );

        if (success) {

            Toast.makeText(
                    this,
                    "Ingredient updated!",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Failed to update ingredient",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}