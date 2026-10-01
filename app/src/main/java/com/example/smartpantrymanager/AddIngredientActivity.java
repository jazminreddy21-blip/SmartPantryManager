package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_ingredient);
        Spinner unitSpinner = findViewById(R.id.spUnit);

        String[] units = {
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

        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                units
        );

        unitAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        unitSpinner.setAdapter(unitAdapter);
        Button saveButton = findViewById(R.id.btnSaveIngredient);
        EditText ingredientName = findViewById(R.id.etIngredientName);
        EditText quantity = findViewById(R.id.etQuantity);
        EditText expiryDate = findViewById(R.id.etExpiryDate);

        saveButton.setOnClickListener(v->{
            String name = ingredientName.getText().toString().trim();
            String quantityText = quantity.getText().toString().trim();

            if(name.isEmpty()) {
                ingredientName.setError("Please enter an ingredient ");
                return;
            }
            if(quantityText.isEmpty()){
                quantity.setError("Please Enter the Quantity");
                return;
            }
            String unit = unitSpinner.getSelectedItem().toString();
            String expiry = expiryDate.getText().toString().trim();

            double quantityValue = Double.parseDouble(quantityText);

            DatabaseHelper dbHelper = new DatabaseHelper(this);

            boolean success = dbHelper.addIngredient(
                    name,
                    quantityValue,
                    unit,
                    expiry
            );

            if (success) {
                Toast.makeText(this, "Ingredient saved!", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to save ingredient", Toast.LENGTH_SHORT).show();
            }


        });

    }

}