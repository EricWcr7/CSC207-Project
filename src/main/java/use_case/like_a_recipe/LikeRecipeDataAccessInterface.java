package use_case.like_a_recipe;

import entity.Recipe;
import java.io.IOException;
import java.util.List;

/**
 * DAO for the Like a Recipe Case, accessing and updating recipe data with "like" functionality.
 */
public interface LikeRecipeDataAccessInterface {

    /**
     * Retrieves a single recipe by its name.
     *
     * @param recipeName the name of the recipe to retrieve.
     * @return the {@link Recipe} object corresponding to the specified recipe name.
     */
    Recipe getOneRecipe(String recipeName);

    /**
     * Updates a specific field of a recipe, such as the number of likes.
     *
     * @param id the unique identifier of the recipe to update.
     * @param likeNumber the new value for the "like" field of the recipe.
     * @throws IOException if an I/O error occurs during the update process.
     */
    void updateRecipeField(String id, String likeNumber) throws IOException;

    void deleteFileFromFileIo();

    void writeRecipesToFile(List<Recipe> updatedRecipes);

    List<Recipe> getCachedRecipes();

    void uploadFileToFileIo();
}
