package use_case.choose_recipe;

import entity.Recipe;

/**
 * DAO for the RecipeSearch Use Case.
 */
public interface ChooseRecipeDataAccessInterface {

    /**
     * Fetches the unique recipe from the API and returns it.
     * @param dishName the name of the dish to search
     * @return a recipe from the API
     */
    Recipe getOneRecipe(String dishName);

    /**
     * Download the json database from File.IO and Upload it again immediately due to auto delete.
     */
    void loadRecipesFromCloud();

    String findFileOnFileIo(String fileName);
}

