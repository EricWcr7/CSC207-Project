package use_case.recipe_search;

import java.util.List;

import entity.Recipe;

/**
 * DAO for the RecipeSearch Use Case.
 */
public interface RecipeSearchDataAccessInterface {

    /**
     * Fetches all recipes from the API and returns a list of recipes.
     * This is intended to gather the full set of recipes for shared storage.
     *
     * @return a list of all recipes from the API
     * @throws Exception if there is an error during the fetch
     */
    List<Recipe> fetchAllRecipes() throws Exception;

    List<Recipe> fetchRecipesByKeyword(String searchKeyword) throws Exception;

    /**
     * Writes a list of recipes to a file and uploads the file to shared storage.
     *
     * @param recipes the list of recipes to write to the file
     */
    void writeRecipesToFile(List<Recipe> recipes);
}


