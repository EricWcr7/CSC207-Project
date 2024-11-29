package use_case.shopping_list;

import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListInteractorTest {

    /**
     * A local implementation of ShoppingListUserDataAccessInterface for testing purposes.
     */
    private static class LocalShoppingListUserDataAccessObject implements ShoppingListUserDataAccessInterface {
        private final List<Recipe> cachedRecipes = new ArrayList<>();

        @Override
        public String getUsername() {
            return "Alice";
        }

        @Override
        public String[] getFavoriteRecipes() {
            return new String[0];
        }

        @Override
        public User get(String username) {
            return null;
        }

        @Override
        public Recipe getOneRecipe(String dishName) {
            return cachedRecipes.stream()
                    .filter(recipe -> recipe.getName().equalsIgnoreCase(dishName))
                    .findFirst()
                    .orElse(null);
        }

        public void addRecipe(Recipe recipe) {
            cachedRecipes.add(recipe);
        }
    }

    @Test
    void testCombinedMeasurements() {
        // Input data
        String username = "Alice";
        String[] recipeNames = {"Pasta", "Salad"};
        ShoppingListInputData inputData = new ShoppingListInputData(username, recipeNames);

        // Create RecipeFactory and recipes
        RecipeFactory recipeFactory = new CommonRecipeFactory();

        // Create recipes with overlapping ingredients to test combination logic
        Map<String, String> pastaIngredients = new HashMap<>();
        pastaIngredients.put("Flour", "1 cup");
        pastaIngredients.put("Tomato Sauce", "1 cup");
        Recipe pastaRecipe = recipeFactory.createRecipe("1", "Pasta", "Main",
                "Flour: 1 cup\nTomato Sauce: 1 cup", pastaIngredients, 10, 2);

        Map<String, String> saladIngredients = new HashMap<>();
        saladIngredients.put("Tomato Sauce", "2 cups");
        saladIngredients.put("Lettuce", "1 head");
        Recipe saladRecipe = recipeFactory.createRecipe("2", "Salad", "Appetizer",
                "Tomato Sauce: 2 cups\nLettuce: 1 head", saladIngredients, 5, 1);

        // Use Local DAO
        LocalShoppingListUserDataAccessObject dao = new LocalShoppingListUserDataAccessObject();
        dao.addRecipe(pastaRecipe);
        dao.addRecipe(saladRecipe);

        // Create a presenter that validates the output
        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(recipeNames, shoppingListOutputData.getRecipeNames());

                Map<String, String> expectedIngredients = new HashMap<>();
                expectedIngredients.put("Flour", "1 cup");
                expectedIngredients.put("Tomato Sauce", "1 cup + 2 cups"); // Expected combined measurement
                expectedIngredients.put("Lettuce", "1 head");

                assertEquals(expectedIngredients, shoppingListOutputData.getIngredients());
            }
        };

        // Execute the interactor
        ShoppingListInputBoundary interactor = new ShoppingListInteractor(successPresenter, dao);
        interactor.execute(inputData);
    }

@Test
    void successTest() {
        // Input data
        String username = "Alice";
        String[] recipeNames = {"Pasta", "Salad"};
        ShoppingListInputData inputData = new ShoppingListInputData(username, recipeNames);

        // Create RecipeFactory and recipes
        RecipeFactory recipeFactory = new CommonRecipeFactory();

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

        // Use Local DAO
        LocalShoppingListUserDataAccessObject dao = new LocalShoppingListUserDataAccessObject();
        dao.addRecipe(pastaRecipe);
        dao.addRecipe(saladRecipe);

        // Create a presenter that validates the output
        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(recipeNames, shoppingListOutputData.getRecipeNames());

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

        // Use Local DAO
        LocalShoppingListUserDataAccessObject dao = new LocalShoppingListUserDataAccessObject();

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

        // Create RecipeFactory and a single valid recipe
        RecipeFactory recipeFactory = new CommonRecipeFactory();

        Map<String, String> pastaIngredients = new HashMap<>();
        pastaIngredients.put("Flour", "2 cups");
        pastaIngredients.put("Tomato Sauce", "1 cup");
        Recipe pastaRecipe = recipeFactory.createRecipe("1", "Pasta", "Main",
                "Boil pasta and add sauce.", pastaIngredients, 10, 2);

        // Use Local DAO
        LocalShoppingListUserDataAccessObject dao = new LocalShoppingListUserDataAccessObject();
        dao.addRecipe(pastaRecipe);

        // Create a presenter that validates the output
        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(recipeNames, shoppingListOutputData.getRecipeNames());

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



