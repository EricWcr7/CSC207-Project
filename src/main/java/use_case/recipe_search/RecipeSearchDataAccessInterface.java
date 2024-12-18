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
    List<Recipe> fetchAllRecipes();

    /**
     * Return the List of Recipe by a searchKeyword input.
     * @param  searchKeyword the searchKeyword.
     * @return a list of recipes from the API.
     * @throws Exception if there is an error during the fetch.
     */
    List<Recipe> fetchRecipesByKeyword(String searchKeyword) throws Exception;

    /**
     * Writes a list of recipes to a json file.
     *
     * @param recipes the list of recipes to write to the file
     */
    void writeRecipesToFile(List<Recipe> recipes);

    /**
     * Upload the written json database to the cloud storage File.IO.
     */
    void uploadFileToFileIo();

    /**
     * Download the json database from File.IO and Upload it again immediately due to auto delete.
     */
    void loadRecipesFromCloud();

    /**
     * Return the list of recipes that contains the searchKeyword (not case sensitive).
     *
     * @param searchKeyword the string we type in the search dialog.
     * @return a list of Recipe.
     */
    List<Recipe> searchRecipes(String searchKeyword);

    /**
     * Return the file key if the file we search exists on File.IO.
     * @param fileName the file name needs to be searched.
     * @return the file key corresponds to the file name if exists.
     */
    String findFileOnFileIo(String fileName);
}

