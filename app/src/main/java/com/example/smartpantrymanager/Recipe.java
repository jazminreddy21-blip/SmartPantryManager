package com.example.smartpantrymanager;

public class Recipe {

    private int id;
    private String name;
    private String instructions;
    private String prepTime;

    public Recipe(int id, String name, String instructions, String prepTime) {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.prepTime = prepTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getPrepTime() {
        return prepTime;
    }
}