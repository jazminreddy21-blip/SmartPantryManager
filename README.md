\# Smart Pantry Manager



Smart Pantry Manager is a Java Android application developed for the Mobile App Development 700 practical assessment.



\## About the Application



The application allows users to manage ingredients that they currently have in their pantry and find recipes that can be prepared using those ingredients.



The main purpose of the application is to avoid suggesting recipes that require ingredients the user does not have.



\## Main Features



\- Add pantry ingredients

\- Edit pantry ingredients

\- Delete pantry ingredients

\- Store ingredient quantity and unit

\- Store optional expiry dates

\- View all pantry ingredients

\- View the recipe collection

\- View individual recipe details

\- View suggested recipes

\- Strict recipe ingredient matching

\- Input validation

\- Persistent local data storage

\- Settings and application information



\## Recipe Matching Rule



A recipe is suggested only when the user has every required ingredient in the required quantity.



Partial matches are not shown as suggestions.



For example, if a recipe requires:



\- 2 eggs

\- 1 cup milk

\- 2 tablespoons sugar



the recipe will only be suggested when all three ingredients are available in the pantry in sufficient quantities.



\## Technology Used



\- Java

\- Android Studio

\- Android SDK

\- SQLite

\- RecyclerView

\- Android Activities

\- Intents

\- XML layouts

\- Git and GitHub



\## Application Screens



The application contains the following main screens:



1\. Pantry

2\. Add Ingredient

3\. Recipes

4\. Recipe Details

5\. Suggested Recipes

6\. Settings



\## Data Storage



The application uses SQLite for local data storage.



Pantry ingredients and recipe information are stored in the local database so that the data remains available when the application is closed and reopened.



\## Running the Application



1\. Open the project in Android Studio.

2\. Allow Gradle to finish syncing.

3\. Start an Android emulator or connect an Android device.

4\. Run the application using the Run button.

5\. Add ingredients to the pantry.

6\. Open Suggested Recipes to see recipes that can currently be prepared.



\## Project Structure



Important project components include:



\- `MainActivity` - Pantry home screen

\- `AddIngredientActivity` - Adds pantry ingredients

\- `EditIngredientActivity` - Edits pantry ingredients

\- `RecipesActivity` - Displays recipes

\- `RecipeDetailActivity` - Displays recipe information

\- `SuggestedRecipesActivity` - Displays recipes that can currently be made

\- `SettingsActivity` - Displays application information

\- `DatabaseHelper` - Handles SQLite database operations

\- `PantryAdapter` - Displays pantry ingredients

\- `RecipeAdapter` - Displays recipes

\- `RecipeSeeder` - Adds the initial recipe collection



Jazmin Reddy



Developed as part of the Mobile App Development 700 practical assessment.

