package use_case.choose_recipe;

import data_access.RecipeDataAccessObject;
import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import org.junit.jupiter.api.Test;
import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeInputData;


import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.Map;

public class ChooseInteractorTest {

    /**
     * A local implementation of UserLikeAndDislikeDataAccessInterface for testing purposes.
     */
    private static class LocalUserDataAccessObject implements ChooseRecipeDataAccessInterface {

        @Override
        public Recipe getOneRecipe(String dishName) {
            return null;
        }

        @Override
        public void loadRecipesFromCloud() {

        }
    }

    @Test
    void successTest() {
        RecipeDataAccessObject recipeDataAccessObject = new RecipeDataAccessObject();
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


    }
}
