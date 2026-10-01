package com.example.smartpantrymanager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SmartPantry.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_PANTRY = "pantry_items";

    private static final String TABLE_RECIPES = "recipes";
    private static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_UNIT = "unit";
    private static final String COLUMN_EXPIRY_DATE = "expiry_date";

    private static final String COLUMN_RECIPE_NAME = "recipe_name";
    private static final String COLUMN_INSTRUCTIONS = "instructions";
    private static final String COLUMN_PREP_TIME = "prep_time";

    private static final String COLUMN_RECIPE_ID = "recipe_id";
    private static final String COLUMN_INGREDIENT_NAME = "ingredient_name";
    private static final String COLUMN_INGREDIENT_QUANTITY = "ingredient_quantity";
    private static final String COLUMN_INGREDIENT_UNIT = "ingredient_unit";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_PANTRY + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_QUANTITY + " REAL NOT NULL, " +
                COLUMN_UNIT + " TEXT NOT NULL, " +
                COLUMN_EXPIRY_DATE + " TEXT" +
                ")";

        db.execSQL(createTable);

        String createRecipesTable = "CREATE TABLE " + TABLE_RECIPES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPE_NAME + " TEXT NOT NULL, " +
                COLUMN_INSTRUCTIONS + " TEXT NOT NULL, " +
                COLUMN_PREP_TIME + " TEXT NOT NULL" +
                ")";

        db.execSQL(createRecipesTable);

        String createRecipeIngredientsTable =
                "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_RECIPE_ID + " INTEGER NOT NULL, " +
                        COLUMN_INGREDIENT_NAME + " TEXT NOT NULL, " +
                        COLUMN_INGREDIENT_QUANTITY + " REAL NOT NULL, " +
                        COLUMN_INGREDIENT_UNIT + " TEXT NOT NULL" +
                        ")";

        db.execSQL(createRecipeIngredientsTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 2) {

            String createRecipesTable = "CREATE TABLE " + TABLE_RECIPES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_RECIPE_NAME + " TEXT NOT NULL, " +
                    COLUMN_INSTRUCTIONS + " TEXT NOT NULL, " +
                    COLUMN_PREP_TIME + " TEXT NOT NULL" +
                    ")";

            db.execSQL(createRecipesTable);

            String createRecipeIngredientsTable =
                    "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            COLUMN_RECIPE_ID + " INTEGER NOT NULL, " +
                            COLUMN_INGREDIENT_NAME + " TEXT NOT NULL, " +
                            COLUMN_INGREDIENT_QUANTITY + " REAL NOT NULL, " +
                            COLUMN_INGREDIENT_UNIT + " TEXT NOT NULL" +
                            ")";

            db.execSQL(createRecipeIngredientsTable);
        }
    }

    public boolean addIngredient(String name, double quantity, String unit, String expiryDate) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_UNIT, unit);
        values.put(COLUMN_EXPIRY_DATE, expiryDate);

        long result = db.insert(TABLE_PANTRY, null, values);

        return result != -1;
    }

    public Cursor getAllIngredients() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_PANTRY,
                null
        );
    }

    public boolean updateIngredient(
            int id,
            String name,
            double quantity,
            String unit,
            String expiryDate
    ) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_UNIT, unit);
        values.put(COLUMN_EXPIRY_DATE, expiryDate);

        int result = db.update(
                TABLE_PANTRY,
                values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        return result > 0;
    }

    public Cursor getIngredientById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_PANTRY + " WHERE " + COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
    }

    public boolean deleteIngredient(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                TABLE_PANTRY,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        return result > 0;
    }

    // Add a recipe
    public long addRecipe(String recipeName, String instructions, String prepTime) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_RECIPE_NAME, recipeName);
        values.put(COLUMN_INSTRUCTIONS, instructions);
        values.put(COLUMN_PREP_TIME, prepTime);

        return db.insert(TABLE_RECIPES, null, values);
    }


    // Add an ingredient to a recipe
    public long addRecipeIngredient(
            long recipeId,
            String ingredientName,
            double quantity,
            String unit
    ) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_RECIPE_ID, recipeId);
        values.put(COLUMN_INGREDIENT_NAME, ingredientName);
        values.put(COLUMN_INGREDIENT_QUANTITY, quantity);
        values.put(COLUMN_INGREDIENT_UNIT, unit);

        return db.insert(TABLE_RECIPE_INGREDIENTS, null, values);
    }


    // Get all recipes
    public Cursor getAllRecipes() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.query(
                TABLE_RECIPES,
                null,
                null,
                null,
                null,
                null,
                COLUMN_RECIPE_NAME + " ASC"
        );
    }


    // Get ingredients for a specific recipe
    public Cursor getRecipeIngredients(int recipeId) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.query(
                TABLE_RECIPE_INGREDIENTS,
                null,
                COLUMN_RECIPE_ID + " = ?",
                new String[]{String.valueOf(recipeId)},
                null,
                null,
                null
        );
    }
    public boolean canMakeRecipe(int recipeId) {

        Cursor recipeIngredients = getRecipeIngredients(recipeId);

        while (recipeIngredients.moveToNext()) {

            String requiredName =
                    recipeIngredients.getString(
                            recipeIngredients.getColumnIndexOrThrow(
                                    "ingredient_name"
                            )
                    );

            double requiredQuantity =
                    recipeIngredients.getDouble(
                            recipeIngredients.getColumnIndexOrThrow(
                                    "ingredient_quantity"
                            )
                    );

            String requiredUnit =
                    recipeIngredients.getString(
                            recipeIngredients.getColumnIndexOrThrow(
                                    "ingredient_unit"
                            )
                    );

            boolean ingredientFound = false;

            Cursor pantryCursor = getAllIngredients();

            while (pantryCursor.moveToNext()) {

                String pantryName =
                        pantryCursor.getString(
                                pantryCursor.getColumnIndexOrThrow("name")
                        );

                double pantryQuantity =
                        pantryCursor.getDouble(
                                pantryCursor.getColumnIndexOrThrow("quantity")
                        );

                String pantryUnit =
                        pantryCursor.getString(
                                pantryCursor.getColumnIndexOrThrow("unit")
                        );

                if (ingredientNamesMatch(requiredName, pantryName)
                        && requiredUnit.equalsIgnoreCase(pantryUnit)) {

                    if (pantryQuantity >= requiredQuantity) {
                        ingredientFound = true;
                    }

                    break;
                }
            }

            pantryCursor.close();

            if (!ingredientFound) {

                recipeIngredients.close();

                return false;
            }
        }

        recipeIngredients.close();

        return true;
    }

    private boolean ingredientNamesMatch(
            String recipeName,
            String pantryName
    ) {

        String recipe = recipeName.toLowerCase().trim();
        String pantry = pantryName.toLowerCase().trim();

        if (recipe.equals(pantry)) {
            return true;
        }

        if (recipe.endsWith("s")
                && recipe.substring(0, recipe.length() - 1).equals(pantry)) {
            return true;
        }

        if (pantry.endsWith("s")
                && pantry.substring(0, pantry.length() - 1).equals(recipe)) {
            return true;
        }

        return false;
    }
}

