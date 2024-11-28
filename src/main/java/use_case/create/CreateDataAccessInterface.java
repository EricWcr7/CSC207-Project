package use_case.create;

import java.util.List;

import entity.Recipe;
import java.io.IOException;

/**
 * The CreateDataAccessInterface defines the contract for managing recipe data
 * in the "Like a Recipe" case. It provides methods for interacting with recipe
 * data, including accessing, updating, and saving recipes in local and cloud storage.
 */
public interface CreateDataAccessInterface {

    /**
     * Deletes the recipe file from the local file system.
     * This method is used when the file is no longer needed or must be replaced.
     */
    void deleteFileFromFileIo();

    /**
     * Writes a list of updated recipes to a file in the local file system.
     *
     * @param updatedRecipes the list of Recipe objects to be saved.
     *                       These typically reflect the latest state of the recipes
     *                       after updates or modifications.
     */
    void writeRecipesToFile(List<Recipe> updatedRecipes);

    /**
     * Retrieves the cached list of recipes stored locally.
     *
     * @return a List of Recipe objects currently cached in the system.
     *         This method provides quick access to recipes without reloading from storage.
     */
    List<Recipe> getCachedRecipes();

    /**
     * Uploads the recipe file to cloud storage.
     * This method is typically used to synchronize local changes with the cloud.
     */
    void uploadFileToFileIo();

    /**
     * Loads the list of recipes from the cloud storage into the system.
     * This ensures that the most up-to-date recipes are available locally.
     */
    void loadRecipesFromCloud();

    /**
     * Retrieves the maximum ID currently in use among the recipes.
     * This is used to generate unique IDs for newly created recipes.
     *
     * @return a String representing the highest ID in the current dataset.
     */
    String getMaxId();

    /**
     * Checks if a recipe with the specified name exists in the current list of recipes.
     *
     * @param dishname the name of the dish to check.
     * @return true if a recipe with the given name exists; false otherwise.
     */
    boolean isNameInRecipes(String dishname);

    /**
     * Saves a newly created recipe to the data storage.
     *
     * @param recipeCreated the Recipe object to be saved. This typically involves
     *                      adding it to the current dataset and updating the file system or cloud storage.
     */
    void saveRecipe(Recipe recipeCreated);
}
