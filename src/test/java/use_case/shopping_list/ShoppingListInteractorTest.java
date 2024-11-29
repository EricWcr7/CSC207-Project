package use_case.shopping_list;

import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListInteractorTest {

    @Test
    void successTest() {
        // Input data
        String username = "Alice";
        String[] recipeNames = {"Pasta", "Salad"};
        ShoppingListInputData inputData = new ShoppingListInputData(username, recipeNames);

        // Create RecipeFactory
        RecipeFactory recipeFactory = new CommonRecipeFactory();

        // Create recipes
        Map<String, String> pastaIngredients = new HashMap<>();
        pastaIngredients.put("Flour", "2 cups");
        pastaIngredients.put("Tomato Sauce", "1 cup");
        Recipe pastaRecipe = recipeFactory.createRecipe("1", "Pasta", "Main",
                "Boil pasta and add sauce.", pastaIngredients, 10, 2);

        Map<String, String> saladIngredients = new HashMap<>();
        saladIngredients.put("Lettuce", "1 head");
        saladIngredients.put("Tomato", "2");
        Recipe saladRecipe = recipeFactory.createRecipe("2", "Salad", "Appetizer",
                "Chop vegetables and mix.", saladIngredients, 5, 1);

        // Use existing DAO
        ShoppingListDataAccessInterface dao = new ShoppingListDataAccessInterface() {
            private final Map<String, Recipe> recipeMap = new HashMap<>() {{
                put("Pasta", pastaRecipe);
                put("Salad", saladRecipe);
            }};

            @Override
            public Recipe getOneRecipe(String dishName) {
                return recipeMap.get(dishName);
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public String[] getFavoriteRecipes() {
                return new String[0];
            }

            @Override
            public entity.User get(String username) {
                return null;
            }
        };

        // Create a presenter that validates the output
        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(recipeNames, shoppingListOutputData.getRecipeNames());

                // Verify the aggregated ingredients
                Map<String, String> expectedIngredients = new HashMap<>();
                expectedIngredients.put("Flour", "2 cups");
                expectedIngredients.put("Tomato Sauce", "1 cup");
                expectedIngredients.put("Lettuce", "1 head");
                expectedIngredients.put("Tomato", "2");
                assertEquals(expectedIngredients, shoppingListOutputData.getIngredients());
            }
        };

        // Execute the interactor
        ShoppingListInputBoundary interactor = new ShoppingListInteractor(successPresenter, dao);
        interactor.execute(inputData);
    }

    @Test
    void emptyRecipeListTest() {
        // Input data with an empty recipe list
        String username = "Alice";
        String[] recipeNames = {};
        ShoppingListInputData inputData = new ShoppingListInputData(username, recipeNames);

        // Use existing DAO
        ShoppingListDataAccessInterface dao = new ShoppingListDataAccessInterface() {
            @Override
            public Recipe getOneRecipe(String dishName) {
                return null;
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public String[] getFavoriteRecipes() {
                return new String[0];
            }

            @Override
            public entity.User get(String username) {
                return null;
            }
        };

        // Create a presenter that validates the output
        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(recipeNames, shoppingListOutputData.getRecipeNames());
                assertTrue(shoppingListOutputData.getIngredients().isEmpty());
            }
        };

        // Execute the interactor
        ShoppingListInputBoundary interactor = new ShoppingListInteractor(successPresenter, dao);
        interactor.execute(inputData);
    }

    @Test
    void missingRecipeTest() {
        // Input data with a recipe name that does not exist
        String username = "Alice";
        String[] recipeNames = {"Pasta", "MissingRecipe"};
        ShoppingListInputData inputData = new ShoppingListInputData(username, recipeNames);

        // Create RecipeFactory
        RecipeFactory recipeFactory = new CommonRecipeFactory();

        // Create a valid recipe
        Map<String, String> pastaIngredients = new HashMap<>();
        pastaIngredients.put("Flour", "2 cups");
        pastaIngredients.put("Tomato Sauce", "1 cup");
        Recipe pastaRecipe = recipeFactory.createRecipe("1", "Pasta", "Main",
                "Boil pasta and add sauce.", pastaIngredients, 10, 2);

        // Use existing DAO
        ShoppingListDataAccessInterface dao = new ShoppingListDataAccessInterface() {
            private final Map<String, Recipe> recipeMap = new HashMap<>() {{
                put("Pasta", pastaRecipe);
            }};

            @Override
            public Recipe getOneRecipe(String dishName) {
                return recipeMap.get(dishName);
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public String[] getFavoriteRecipes() {
                return new String[0];
            }

            @Override
            public entity.User get(String username) {
                return null;
            }
        };

        // Create a presenter that validates the output
        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(recipeNames, shoppingListOutputData.getRecipeNames());

                // Verify the aggregated ingredients
                Map<String, String> expectedIngredients = new HashMap<>();
                expectedIngredients.put("Flour", "2 cups");
                expectedIngredients.put("Tomato Sauce", "1 cup");
                assertEquals(expectedIngredients, shoppingListOutputData.getIngredients());
            }
        };

        // Execute the interactor
        ShoppingListInputBoundary interactor = new ShoppingListInteractor(successPresenter, dao);
        interactor.execute(inputData);
    }
}


