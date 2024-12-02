package use_case.delete;

import entity.Recipe;

import java.util.List;

/**
 * The data access interface for deleting recipes from all_recipes.json.
 */

public interface DeleteDataAccessInterface {

    /**
     * Deletes a recipe from all_recipes.json based on the recipe name.
     *
     */
    void deleteFileFromFileIo();

    void writeRecipesToFile(List<Recipe> recipes);

    void uploadFileToFileIo();

    void loadRecipesFromCloud();

    List<Recipe> getCachedRecipes();

    void removeRecipeByName(String recipeName);

    String findFileOnFileIo(String fileName);
}

