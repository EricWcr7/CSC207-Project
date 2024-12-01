package use_case.like_and_dislike_a_recipe;

import java.util.List;

import entity.Recipe;

/**
 * DAO for the Like a Recipe Case, accessing and updating recipe data with "like" functionality.
 */
public interface LikeAndDislikeRecipeDataAccessInterface {

    /**
     * Retrieves a single recipe by its name.
     *
     * @param recipeName the name of the recipe to retrieve.
     * @return the {@link Recipe} object corresponding to the specified recipe name.
     */
    Recipe getOneRecipe(String recipeName);

    /**
     * Deletes the recipe data file from the file I/O system.
     * This method removes the existing recipe data file, typically as part of a process to replace
     * it with an updated version.
     */
    void deleteFileFromFileIo();

    /**
     * Writes updated recipes to a file.
     * This method saves the provided list of recipes to a data file for persistence.
     * @param updatedRecipes the list of updated {@link Recipe} objects to be saved.
     */
    void writeRecipesToFile(List<Recipe> updatedRecipes);

    /**
     * Retrieves the cached recipes from the data source.
     * This method fetches all recipes currently stored in the cache.
     * @return a list of {@link Recipe} objects representing the cached recipes.
     */
    List<Recipe> getCachedRecipes();

    /**
     * Uploads the recipe data file to the file I/O system.
     * This method ensures the latest version of the recipe data file is stored and accessible
     * within the file system.
     */
    void uploadFileToFileIo();

    /**
     * Updates the list of recipes with the changes provided.
     * @param updatedRecipes a list of {@link Recipe} objects containing the updated data for the recipes.
     */
    void updateChangedRecipes(List<Recipe> updatedRecipes);
}
