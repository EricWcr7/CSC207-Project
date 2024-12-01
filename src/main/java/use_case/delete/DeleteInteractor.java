package use_case.delete;

import entity.Recipe;

import java.util.List;
import data_access.RecipeDataAccessObject;

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

//    @Override
//    public void deleteRecipe(DeleteInputData inputData) {
//        try {
//
//            deleteDataAccess.deleteFileFromFileIo();
//            deleteDataAccess.uploadFileToFileIo();
//
//            deleteOutputBoundary.prepareSuccessView();
//        }
//        catch (Exception e) {
//
//            deleteOutputBoundary.prepareFailureView();
//        }
//    }

    @Override
    public void deleteUserRecipe(DeleteInputData inputData) {
        try {

            deleteUserDataAccess.deleteRecipeForUser(inputData.getRecipeName());

            deleteOutputBoundary.prepareSuccessView();
        }
        catch (Exception e) {

            deleteOutputBoundary.prepareFailureView();
        }
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

        // Step 3: Load recipes from the cloud for further deletion
        recipeDataAccessObject.loadRecipesFromCloud();

        // Step 4: Check if the recipe exists in the cloud storage
        if (recipeDataAccessObject.isNameInRecipes(recipeName)) {
            // Step 4.1: Remove the recipe from the cached cloud recipes
            recipeDataAccessObject.removeRecipeByName(recipeName);

            // Step 4.2: Update the local JSON file with the updated list of recipes
            List<Recipe> updatedRecipes = recipeDataAccessObject.getCachedRecipes();
            recipeDataAccessObject.writeRecipesToFile(updatedRecipes);

            // Step 4.3: Synchronize changes with the cloud
            recipeDataAccessObject.deleteFileFromFileIo();
            recipeDataAccessObject.uploadFileToFileIo();

        }
    }
}
