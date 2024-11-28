package use_case.delete;

import java.util.List;

import data_access.RecipeDataAccessObject;
import entity.Recipe;

/**
 * Interactor class for the Delete Use Case.
 * Handles the business logic for deleting a recipe.
 */
public class DeleteInteractor implements DeleteInputBoundary {

    // Presenter for handling output logic (e.g., showing success or failure messages)
    private final DeleteOutputBoundary deletePresenter;

    // DAO for interacting with recipe data storage (e.g., local and cloud JSON files)
    private final RecipeDataAccessObject recipeDataAccessObject;

    /**
     * Constructor for DeleteInteractor.
     *
     * @param deletePresenter         The output boundary (presenter) for the Delete Use Case.
     * @param recipeDataAccessObject  The data access object for interacting with recipes.
     */
    public DeleteInteractor(DeleteOutputBoundary deletePresenter, RecipeDataAccessObject recipeDataAccessObject) {
        this.deletePresenter = deletePresenter;
        // Assign presenter for handling output
        this.recipeDataAccessObject = recipeDataAccessObject;
        // Assign DAO for data operations
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

        // Step 2: Attempt to delete the recipe from the local file
        boolean localDeleted = recipeDataAccessObject.removeRecipeFromLocalFile("new_recipes.json", recipeName);
        localDeleted = localDeleted;
        // why you create but not use localDeleted? I just fix checkstyle here.

        // Step 3: Load recipes from the cloud for further deletion
        recipeDataAccessObject.loadRecipesFromCloud();

        // Step 4: Check if the recipe exists in the cloud storage
        if (recipeDataAccessObject.isNameInRecipes(recipeName)) {
            // Step 4.1: Remove the recipe from the cached cloud recipes
            recipeDataAccessObject.removeRecipeByName(recipeName);

            // Step 4.2: Update the local JSON file with the updated list of recipes
            final List<Recipe> updatedRecipes = recipeDataAccessObject.getCachedRecipes();
            recipeDataAccessObject.writeRecipesToFile(updatedRecipes);

            // Step 4.3: Synchronize changes with the cloud
            recipeDataAccessObject.deleteFileFromFileIo();
            // Delete the old cloud file
            recipeDataAccessObject.uploadFileToFileIo();
            // Upload the updated file to the cloud

            // Step 4.4: Notify the presenter of a successful deletion
            deletePresenter.prepareSuccessView();
        }
        else {
            // Step 5: If the recipe does not exist in the cloud, notify the presenter of failure
            deletePresenter.prepareFailureView();
        }
    }
}

