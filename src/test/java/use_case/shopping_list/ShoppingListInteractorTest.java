package use_case.shopping_list;

import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListInteractorTest {

    private static class LocalShoppingListRecipeDataAccessObject implements ShoppingListRecipeDataAccessInterface {
        private final List<Recipe> cachedRecipes = new ArrayList<>();

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
    void successTest() {
        // Input data
        String username = "Alice";
        String[] recipeNames = {"Pasta", "Salad"};
        ShoppingListInputData inputData = new ShoppingListInputData(username, recipeNames);

        // Create RecipeFactory and recipes
        RecipeFactory recipeFactory = new CommonRecipeFactory();

        // Create Map<String, String> for ingredients
        Map<String, String> pastaIngredients = new HashMap<>();
        pastaIngredients.put("Flour", "2 cups");
        pastaIngredients.put("Tomato Sauce", "1 cup");

        Map<String, String> saladIngredients = new HashMap<>();
        saladIngredients.put("Lettuce", "1 head");
        saladIngredients.put("Tomato", "2");

        // Create recipes with the Map
        Recipe pastaRecipe = recipeFactory.createRecipe("1", "Pasta", "Main",
                "Boil pasta and add sauce.", pastaIngredients, 10, 2);
        Recipe saladRecipe = recipeFactory.createRecipe("2", "Salad", "Appetizer",
                "Chop vegetables and mix.", saladIngredients, 5, 1);

        // Use Local DAO
        LocalShoppingListRecipeDataAccessObject dao = new LocalShoppingListRecipeDataAccessObject();
        dao.addRecipe(pastaRecipe);
        dao.addRecipe(saladRecipe);

        // Create a presenter that validates the output
        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(recipeNames, shoppingListOutputData.getRecipeNames());

                // Validate that the shopping list contains only ingredient names (keys of the map)
                Set<String> expectedIngredients = new HashSet<>();
                expectedIngredients.add("Flour");
                expectedIngredients.add("Tomato Sauce");
                expectedIngredients.add("Lettuce");
                expectedIngredients.add("Tomato");

                assertEquals(expectedIngredients, shoppingListOutputData.getIngredients());
            }
        };

        // Execute the interactor
        ShoppingListInputBoundary interactor = new ShoppingListInteractor(successPresenter, dao);
        interactor.execute(inputData);
    }

    @Test
    void capitalizeFirstLetterTest() throws Exception {
        // Use reflection to access the private helper method
        ShoppingListInteractor interactor = new ShoppingListInteractor(null, null);
        Method capitalizeMethod = ShoppingListInteractor.class.getDeclaredMethod("capitalizeFirstLetter", String.class);
        capitalizeMethod.setAccessible(true);

        // Test cases for the helper method
        assertEquals("Flour", capitalizeMethod.invoke(interactor, "flour"));
        assertEquals("Tomato Sauce", capitalizeMethod.invoke(interactor, "tomato sauce"));
        assertEquals("Lettuce", capitalizeMethod.invoke(interactor, "lettuce"));
        assertEquals("", capitalizeMethod.invoke(interactor, ""));
        assertNull(capitalizeMethod.invoke(interactor, (Object) null));
    }

    @Test
    void emptyRecipeListTest() {
        // Input data with an empty recipe list
        String username = "Alice";
        String[] recipeNames = {};
        ShoppingListInputData inputData = new ShoppingListInputData(username, recipeNames);

        // Use Local DAO
        LocalShoppingListRecipeDataAccessObject dao = new LocalShoppingListRecipeDataAccessObject();

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
    void nullAndEmptyRecipeNameTest() {
        // Input data with null and "null" recipe names
        String username = "Alice";
        String[] recipeNames = {"Pasta", null, "null", "Salad"};
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
        LocalShoppingListRecipeDataAccessObject dao = new LocalShoppingListRecipeDataAccessObject();
        dao.addRecipe(pastaRecipe);
        dao.addRecipe(saladRecipe);

        // Create a presenter that validates the output
        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(recipeNames, shoppingListOutputData.getRecipeNames());

                Set<String> expectedIngredients = new HashSet<>();
                expectedIngredients.add("Flour");
                expectedIngredients.add("Tomato Sauce");
                expectedIngredients.add("Lettuce");
                expectedIngredients.add("Tomato");

                assertEquals(expectedIngredients, shoppingListOutputData.getIngredients());
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
        LocalShoppingListRecipeDataAccessObject dao = new LocalShoppingListRecipeDataAccessObject();
        dao.addRecipe(pastaRecipe);

        // Create a presenter that validates the output
        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(recipeNames, shoppingListOutputData.getRecipeNames());

                Set<String> expectedIngredients = new HashSet<>();
                expectedIngredients.add("Flour");
                expectedIngredients.add("Tomato Sauce");

                assertEquals(expectedIngredients, shoppingListOutputData.getIngredients());
            }
        };

        // Execute the interactor
        ShoppingListInputBoundary interactor = new ShoppingListInteractor(successPresenter, dao);
        interactor.execute(inputData);
    }

    @Test
    void emptyIngredientsTest() {
        // Input data with a recipe that has no ingredients
        String username = "Alice";
        String[] recipeNames = {"EmptyRecipe"};
        ShoppingListInputData inputData = new ShoppingListInputData(username, recipeNames);

        // Create RecipeFactory and a recipe with no ingredients
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        Map<String, String> emptyIngredients = new HashMap<>();
        Recipe emptyRecipe = recipeFactory.createRecipe("3", "EmptyRecipe", "Main",
                "No ingredients required.", emptyIngredients, 0, 0);

        // Use Local DAO
        LocalShoppingListRecipeDataAccessObject dao = new LocalShoppingListRecipeDataAccessObject();
        dao.addRecipe(emptyRecipe);

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
}
