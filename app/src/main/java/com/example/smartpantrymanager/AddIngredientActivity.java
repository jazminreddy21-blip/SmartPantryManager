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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                units
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        unitSpinner.setAdapter(adapter);

        Button saveButton = findViewById(R.id.btnSaveIngredient);
        EditText ingredientName = findViewById(R.id.etIngredientName);
        EditText quantity = findViewById(R.id.etQuantity);

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
            Toast.makeText(this,"Ingredients are valid", Toast.LENGTH_SHORT).show();


        });

    }

}