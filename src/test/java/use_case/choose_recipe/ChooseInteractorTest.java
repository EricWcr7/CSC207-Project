package use_case.choose_recipe;

import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ChooseInteractorTest {

    /**
     * A local implementation of CreateDataAccessInterface for testing purposes.
     */
    private static class LocalRecipeRecipeDataAccessObject implements ChooseRecipeDataAccessInterface {
        private final List<Recipe> recipes = new ArrayList<>();
        private String maxId = "0";
        private List<Recipe> cachedRecipes = new ArrayList<>();


        @Override
        public Recipe getOneRecipe(String dishName) {

            return cachedRecipes.get(0);
        }

        @Override
        public void loadRecipesFromCloud() {

        }

        @Override
        public String findFileOnFileIo(String fileName) {
            return "";
        }

        public void saveRecipe(Recipe saladRecipe) {
            cachedRecipes.add(saladRecipe);
        }
    }

    @Test
    void successTest() {
        LocalRecipeRecipeDataAccessObject recipeDataAccessObject = new LocalRecipeRecipeDataAccessObject();
        // Input data
        String username = "Lana Del Rey";
        String[] recipeNames = {"Pasta", "Salad"};
        String recipeName = "Salad";
        String[] favoriteRecipes = {"Pasta", "Salad"};
        ChooseRecipeInputData inputData = new ChooseRecipeInputData(recipeName, username, favoriteRecipes);

        // Create RecipeFactory and recipes
        RecipeFactory recipeFactory = new CommonRecipeFactory();

        Map<String, String> saladIngredients = new HashMap<>();
        saladIngredients.put("Tomato Sauce", "2 tablespoons"); // Overlapping ingredient
        saladIngredients.put("Lettuce", "1 head");
        saladIngredients.put("Cucumber", "1 medium");
        saladIngredients.put("Olive Oil", "2 tablespoons");
        saladIngredients.put("Lemon Juice", "1 tablespoon");
        saladIngredients.put("Salt", "to taste");
        Recipe saladRecipe = recipeFactory.createRecipe("1", "Salad", "Side",
                "Lettuce: 1 head\nCucumber: 1 medium\nTomato Sauce: 2 tablespoons\nOlive Oil: 2 tablespoons\nLemon Juice: 1 tablespoon\nSalt: to taste",
                saladIngredients, 5, 4);
        ChooseRecipeOutputBoundary presenter = new ChooseRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(ChooseRecipeOutputData outputData) {            // Assertions to validate the output data
                Map<String, String> ingredients = new HashMap<>();
                ingredients.put("Tomato Sauce", "2 tablespoons"); // Overlapping ingredient
                ingredients.put("Lettuce", "1 head");
                ingredients.put("Cucumber", "1 medium");
                ingredients.put("Olive Oil", "2 tablespoons");
                ingredients.put("Lemon Juice", "1 tablespoon");
                ingredients.put("Salt", "to taste");
                assertEquals("Salad", outputData.getDishName());
                assertEquals("Lettuce: 1 head\nCucumber: 1 medium\nTomato Sauce: 2 tablespoons\nOlive Oil: 2 tablespoons\nLemon Juice: 1 tablespoon\nSalt: to taste",
                        outputData.getDishInstructions());
                assertEquals("Salt: to taste\n" +
                        "Lettuce: 1 head\n" +
                        "Lemon Juice: 1 tablespoon\n" +
                        "Olive Oil: 2 tablespoons\n" +
                        "Cucumber: 1 medium\n" +
                        "Tomato Sauce: 2 tablespoons",outputData.getDishIngredients());
                assertEquals(5, outputData.getLikeNumber());
                assertEquals(4, outputData.getDislikeNumber());
                assertEquals("Lana Del Rey", outputData.getUsername());
                assertEquals(2, outputData.getFavoriteRecipes().length);
                assertEquals("Pasta", outputData.getFavoriteRecipes()[0]);
                assertEquals("Salad", outputData.getFavoriteRecipes()[1]);
            }
        };
        recipeDataAccessObject.saveRecipe(saladRecipe);
        ChooseRecipeInteractor interactor = new ChooseRecipeInteractor(recipeDataAccessObject, presenter);
        interactor.execute(inputData);

    }
}
