package use_case.delete;

import data_access.RecipeDataAccessObject;
import entity.Recipe;

import java.util.List;

public class DeleteInteractor implements DeleteInputBoundary {

    private final DeleteDataAccessInterface deleteDataAccess;
    private final DeleteUserDataAccessInterface deleteUserDataAccess;
    private final DeleteOutputBoundary deleteOutputBoundary;
    private final RecipeDataAccessObject recipeDataAccessObject;

    // 构造方法
    public DeleteInteractor(DeleteDataAccessInterface deleteDataAccess,
                            DeleteUserDataAccessInterface deleteUserDataAccess,
                            DeleteOutputBoundary deleteOutputBoundary,
                            RecipeDataAccessObject recipeDataAccessObject) {
        this.deleteDataAccess = deleteDataAccess;
        this.deleteUserDataAccess = deleteUserDataAccess;
        this.deleteOutputBoundary = deleteOutputBoundary;
        this.recipeDataAccessObject = recipeDataAccessObject;
    }

    @Override
    public void deleteUserRecipe(DeleteInputData inputData) {
        deleteUserDataAccess.deleteRecipeForUser(inputData.getRecipeName());
        deleteOutputBoundary.prepareSuccessView();
    }

    /**
     * Executes the delete logic for a given recipe name.
     * This method first deletes the recipe from the local file (`new_recipes.json`) and then
     * checks and removes it from the cloud data. If the recipe is successfully removed from both
     * locations, it updates the cloud file and notifies the presenter of success. Otherwise, it
     * notifies the presenter of failure.
     *
     * @param inputData The input data containing the recipe name to be deleted.
     */
    @Override
    public void execute(DeleteInputData inputData) {
        // Step 1: Extract the recipe name from input data
        final String recipeName = inputData.getRecipeName();

        deleteDataAccess.findFileOnFileIo("all_recipes.json");
        // Step 3: Load recipes from the cloud for further deletion
        deleteDataAccess.loadRecipesFromCloud();

        // Step 4.1: Remove the recipe from the cached cloud recipes
        deleteDataAccess.removeRecipeByName(recipeName);

        final List<Recipe> updatedRecipes = deleteDataAccess.getCachedRecipes();
        deleteDataAccess.writeRecipesToFile(updatedRecipes);

        deleteDataAccess.deleteFileFromFileIo();
        deleteDataAccess.uploadFileToFileIo();

    }
}
