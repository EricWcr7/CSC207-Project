package use_case.delete;

import java.util.List;

import entity.Recipe;

/**
 * Interface for accessing and managing recipe data across various storage systems.
 * This interface provides methods for interacting with recipe data, including operations such
 * as fetching recipes from APIs, searching recipes in cached data, loading and saving recipes
 * to files, and managing recipe storage on cloud platforms like File.io.
 */
public interface DeleteDataAccessInterface {
    /**
     * Removes a recipe from a local JSON file based on the recipe name.
     *
     * @param filePath   The path of the JSON file containing the recipes.
     * @param recipeName The name of the recipe to be removed.
     * @return true if the recipe was successfully removed and the file updated; false otherwise.
     */
    boolean removeRecipeFromLocalFile(String filePath, String recipeName);

    /**
     * Removes a r@ecipe with the specified name from the cached recipes list.
     *
     * @param recipeName The name of the recipe to remove.
     */
    void removeRecipeByName(String recipeName);

    /**
     * Loads the list of recipes from cloud storage.
     * This method fetches the recipes stored on a cloud platform (e.g., File.io) and updates
     * the local cached recipes list with the data retrieved.
     */
    void loadRecipesFromCloud();

    /**
     * Checks if a recipe with the specified name exists in the cached recipes list.
     * This method searches the in-memory cache of recipes to see if a recipe with the given
     * name is present.
     *
     * @param recipeName the name of the recipe to check
     * @return {@code true} if a recipe with the specified name exists in the cache; {@code false} otherwise
     */
    boolean isNameInRecipes(String recipeName);

    /**
     * Retrieves the list of cached recipes.
     * This method returns the in-memory list of recipes currently stored in the cache.
     *
     * @return a {@link List} of {@link Recipe} objects representing the cached recipes
     */
    List<Recipe> getCachedRecipes();

    /**
     * Writes the updated list of recipes to a local JSON file.
     * This method takes a list of recipes and serializes it to a JSON file at a specified location.
     *
     * @param updatedRecipes the list of recipes to be written to the file
     */
    void writeRecipesToFile(List<Recipe> updatedRecipes);

    /**
     * Deletes the recipe file from File.io cloud storage.
     * This method sends a DELETE request to the File.io API to remove the file identified
     * by its unique key from the cloud storage.
     */
    void deleteFileFromFileIo();

    /**
     * Uploads the recipe file to File.io cloud storage.
     * This method sends a POST request to the File.io API to upload the updated recipe file
     * to the cloud storage.
     */
    void uploadFileToFileIo();
}
