package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button addButton = findViewById(R.id.btnAddIngredient);
        addButton.setOnClickListener(v ->{
        Intent intent = new Intent(MainActivity.this, AddIngredientActivity.class);
        startActivity(intent);
        });



    }
}