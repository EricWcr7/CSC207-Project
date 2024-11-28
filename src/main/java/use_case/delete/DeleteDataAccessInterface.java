package use_case.delete;

import entity.Recipe;

import java.util.List;

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

    void loadRecipesFromCloud();

    boolean isNameInRecipes(String recipeName);

    List<Recipe> getCachedRecipes();

    void writeRecipesToFile(List<Recipe> updatedRecipes);

    void deleteFileFromFileIo();

    void uploadFileToFileIo();
}
