package use_case.create_recipe;

import java.util.List;

import entity.Recipe;

/**
 * DAO for the Like a Recipe Case, accessing and updating recipe data with "like" functionality.
 */
public interface CreateRecipeDataAccessInterface {

    /**
     * Delete File From FileIo.
     */
    void deleteFileFromFileIo();

    /**
     * Write Recipes To File.
     * @param updatedRecipes the new Recipes.
     */
    void writeRecipesToFile(List<Recipe> updatedRecipes);

    /**
     * Return the List of recipe.
     * @return List of recipe when this method execute.
     */
    List<Recipe> getCachedRecipes();

    /**
     * Upload File To FileIo.
     */
    void uploadFileToFileIo();

    /**
     * Load Recipes From Cloud.
     */
    void loadRecipesFromCloud();

    /**
     * Return the Max of Id.
     * @return Max of Id when this method execute.
     */
    String getMaxId();

    /**
     * Return the Boolean of name is in Recipes.
     * @param dishname the name i what to know if that exist in Recipes.
     * @return Boolean of name is in Recipes when this method execute.
     */
    boolean isNameInRecipes(String dishname);

    /**
     * Save Recipe.
     * @param recipeCreated the new created Recipe.
     */
    void saveRecipe(Recipe recipeCreated);

    String findFileOnFileIo(String fileName);
}
