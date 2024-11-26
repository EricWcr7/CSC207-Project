package use_case.create;

import entity.Recipe;

import java.io.IOException;
import java.util.List;

/**
 * DAO for the Like a Recipe Case, accessing and updating recipe data with "like" functionality.
 */
public interface CreateDataAccessInterface {

    void deleteFileFromFileIo();

    void writeRecipesToFile(List<Recipe> updatedRecipes);

    List<Recipe> getCachedRecipes();

    void uploadFileToFileIo();

    void loadRecipesFromCloud();

    String getMaxId();

    boolean isNameInRecipes(String dishname);

    void saveRecipe(Recipe recipeCreated);
}
