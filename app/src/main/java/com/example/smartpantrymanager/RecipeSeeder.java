package com.example.smartpantrymanager;

public class RecipeSeeder {

    public static void seedRecipes(DatabaseHelper dbHelper) {

        // Prevent recipes from being added every time the app starts
        if (dbHelper.getAllRecipes().getCount() > 0) {
            return;
        }

        // Recipe 1 - Pancakes
        long pancakesId = dbHelper.addRecipe(
                "Pancakes",
                "Mix the flour, sugar and eggs. Add milk gradually and mix until smooth. Cook on a heated pan until golden on both sides.",
                "20 minutes"
        );

        dbHelper.addRecipeIngredient(pancakesId, "flour", 2, "cups");
        dbHelper.addRecipeIngredient(pancakesId, "milk", 1, "cups");
        dbHelper.addRecipeIngredient(pancakesId, "eggs", 2, "pieces");
        dbHelper.addRecipeIngredient(pancakesId, "sugar", 2, "tablespoons");


        // Recipe 2 - Scrambled Eggs
        long scrambledEggsId = dbHelper.addRecipe(
                "Scrambled Eggs",
                "Beat the eggs and cook them in a pan while stirring until scrambled and fully cooked.",
                "10 minutes"
        );

        dbHelper.addRecipeIngredient(scrambledEggsId, "eggs", 3, "pieces");
        dbHelper.addRecipeIngredient(scrambledEggsId, "milk", 2, "tablespoons");
        dbHelper.addRecipeIngredient(scrambledEggsId, "butter", 1, "tablespoons");


        // Recipe 3 - French Toast
        long frenchToastId = dbHelper.addRecipe(
                "French Toast",
                "Dip bread in a mixture of eggs, milk and sugar. Fry both sides until golden brown.",
                "15 minutes"
        );

        dbHelper.addRecipeIngredient(frenchToastId, "bread", 4, "pieces");
        dbHelper.addRecipeIngredient(frenchToastId, "eggs", 2, "pieces");
        dbHelper.addRecipeIngredient(frenchToastId, "milk", 0.5, "cups");
        dbHelper.addRecipeIngredient(frenchToastId, "sugar", 1, "tablespoons");


        // Recipe 4 - Omelette
        long omeletteId = dbHelper.addRecipe(
                "Omelette",
                "Beat the eggs and cook in a pan. Add cheese and vegetables, then fold the omelette.",
                "15 minutes"
        );

        dbHelper.addRecipeIngredient(omeletteId, "eggs", 3, "pieces");
        dbHelper.addRecipeIngredient(omeletteId, "cheese", 0.5, "cups");
        dbHelper.addRecipeIngredient(omeletteId, "tomatoes", 1, "pieces");
        dbHelper.addRecipeIngredient(omeletteId, "onion", 0.5, "pieces");


        // Recipe 5 - Pasta
        long pastaId = dbHelper.addRecipe(
                "Tomato Pasta",
                "Cook the pasta until tender. Prepare a tomato sauce with onion and garlic, then combine with the pasta.",
                "25 minutes"
        );

        dbHelper.addRecipeIngredient(pastaId, "pasta", 2, "cups");
        dbHelper.addRecipeIngredient(pastaId, "tomatoes", 2, "pieces");
        dbHelper.addRecipeIngredient(pastaId, "onion", 1, "pieces");
        dbHelper.addRecipeIngredient(pastaId, "garlic", 2, "pieces");


        // Recipe 6 - Grilled Cheese Sandwich
        long grilledCheeseId = dbHelper.addRecipe(
                "Grilled Cheese Sandwich",
                "Place cheese between two slices of bread and grill until the bread is golden and the cheese has melted.",
                "10 minutes"
        );

        dbHelper.addRecipeIngredient(grilledCheeseId, "bread", 2, "pieces");
        dbHelper.addRecipeIngredient(grilledCheeseId, "cheese", 2, "pieces");
        dbHelper.addRecipeIngredient(grilledCheeseId, "butter", 1, "tablespoons");


        // Recipe 7 - Banana Smoothie
        long smoothieId = dbHelper.addRecipe(
                "Banana Smoothie",
                "Blend bananas, milk and sugar until smooth.",
                "5 minutes"
        );

        dbHelper.addRecipeIngredient(smoothieId, "bananas", 2, "pieces");
        dbHelper.addRecipeIngredient(smoothieId, "milk", 1, "cups");
        dbHelper.addRecipeIngredient(smoothieId, "sugar", 1, "tablespoons");


        // Recipe 8 - Garlic Bread
        long garlicBreadId = dbHelper.addRecipe(
                "Garlic Bread",
                "Mix butter and garlic. Spread the mixture over bread and bake until golden.",
                "15 minutes"
        );

        dbHelper.addRecipeIngredient(garlicBreadId, "bread", 4, "pieces");
        dbHelper.addRecipeIngredient(garlicBreadId, "butter", 2, "tablespoons");
        dbHelper.addRecipeIngredient(garlicBreadId, "garlic", 2, "pieces");


        // Recipe 9 - Egg Sandwich
        long eggSandwichId = dbHelper.addRecipe(
                "Egg Sandwich",
                "Boil the eggs, slice them and place them between slices of bread.",
                "15 minutes"
        );

        dbHelper.addRecipeIngredient(eggSandwichId, "eggs", 2, "pieces");
        dbHelper.addRecipeIngredient(eggSandwichId, "bread", 2, "pieces");


        // Recipe 10 - Tomato Omelette
        long tomatoOmeletteId = dbHelper.addRecipe(
                "Tomato Omelette",
                "Beat the eggs and cook them with chopped tomatoes and onion.",
                "15 minutes"
        );

        dbHelper.addRecipeIngredient(tomatoOmeletteId, "eggs", 3, "pieces");
        dbHelper.addRecipeIngredient(tomatoOmeletteId, "tomatoes", 2, "pieces");
        dbHelper.addRecipeIngredient(tomatoOmeletteId, "onion", 1, "pieces");


        // Recipe 11 - Buttered Pasta
        long butteredPastaId = dbHelper.addRecipe(
                "Buttered Pasta",
                "Cook the pasta until tender and mix with butter and cheese.",
                "20 minutes"
        );

        dbHelper.addRecipeIngredient(butteredPastaId, "pasta", 2, "cups");
        dbHelper.addRecipeIngredient(butteredPastaId, "butter", 2, "tablespoons");
        dbHelper.addRecipeIngredient(butteredPastaId, "cheese", 0.5, "cups");


        // Recipe 12 - Banana Pancakes
        long bananaPancakesId = dbHelper.addRecipe(
                "Banana Pancakes",
                "Mash the bananas and mix with eggs, flour and milk. Cook pancakes on a heated pan.",
                "20 minutes"
        );

        dbHelper.addRecipeIngredient(bananaPancakesId, "bananas", 2, "pieces");
        dbHelper.addRecipeIngredient(bananaPancakesId, "eggs", 2, "pieces");
        dbHelper.addRecipeIngredient(bananaPancakesId, "flour", 1, "cups");
        dbHelper.addRecipeIngredient(bananaPancakesId, "milk", 0.5, "cups");


        // Recipe 13 - Tomato Sandwich
        long tomatoSandwichId = dbHelper.addRecipe(
                "Tomato Sandwich",
                "Place sliced tomatoes and cheese between slices of bread.",
                "5 minutes"
        );

        dbHelper.addRecipeIngredient(tomatoSandwichId, "bread", 2, "pieces");
        dbHelper.addRecipeIngredient(tomatoSandwichId, "tomatoes", 1, "pieces");
        dbHelper.addRecipeIngredient(tomatoSandwichId, "cheese", 1, "pieces");


        // Recipe 14 - Cheese Omelette
        long cheeseOmeletteId = dbHelper.addRecipe(
                "Cheese Omelette",
                "Beat the eggs, cook them in a pan and add cheese before folding.",
                "10 minutes"
        );

        dbHelper.addRecipeIngredient(cheeseOmeletteId, "eggs", 2, "pieces");
        dbHelper.addRecipeIngredient(cheeseOmeletteId, "cheese", 0.5, "cups");


        // Recipe 15 - Garlic Butter Pasta
        long garlicPastaId = dbHelper.addRecipe(
                "Garlic Butter Pasta",
                "Cook the pasta. Fry garlic in butter and mix with the cooked pasta.",
                "20 minutes"
        );

        dbHelper.addRecipeIngredient(garlicPastaId, "pasta", 2, "cups");
        dbHelper.addRecipeIngredient(garlicPastaId, "garlic", 2, "pieces");
        dbHelper.addRecipeIngredient(garlicPastaId, "butter", 2, "tablespoons");
    }
}