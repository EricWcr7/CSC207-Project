package use_case.choose_recipe;

import entity.Recipe;

/**
 * DAO for the RecipeSearch Use Case.
 */
public interface ChooseRecipeDataAccessInterface {

//    List<CommonRecipe> searchRecipes(String keyword);

    /**
     * Fetches the unique recipe from the API and returns it.
     * @param dishName the name of the dish to search
     * @return a recipe from the API
     */
    Recipe getOneRecipe(String dishName);

    void loadRecipesFromCloud();
}


