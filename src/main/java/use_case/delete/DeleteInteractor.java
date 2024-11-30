package use_case.delete;

import java.util.List;
import entity.Recipe;

/**
 * Interactor class for the Delete Use Case.
 * Handles the business logic for deleting a recipe from both all_users.json and all_recipes.json.
 */
public class DeleteInteractor implements DeleteInputBoundary {

    private final DeleteOutputBoundary deletePresenter;
    private final DeleteDataAccessInterface recipeDataAccessObject;
    private final DUserDataAccessInterface userDataAccessObject;

    /**
     * Constructor for DeleteInteractor.
     *
     * @param deletePresenter        The presenter to provide success/failure views.
     * @param recipeDataAccessObject DAO for interacting with global recipe storage.
     * @param userDataAccessObject   DAO for interacting with user data storage.
     */
    public DeleteInteractor(DeleteOutputBoundary deletePresenter,
                            DeleteDataAccessInterface recipeDataAccessObject,
                            DUserDataAccessInterface userDataAccessObject) {
        this.deletePresenter = deletePresenter;
        this.recipeDataAccessObject = recipeDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(DeleteInputData inputData) {
        String recipeName = inputData.getRecipeName();
        String username = inputData.getUsername();

        // Step 1: Remove the recipe from user's "createdRecipes" list
        if (!removeRecipeFromUser(username, recipeName)) {
            deletePresenter.prepareFailureView(); // Notify failure if unable to remove from user
            return;
        }

        // Step 2: Sync user data to cloud after modification
        userDataAccessObject.syncUsersToCloud();

        // Step 3: Remove the recipe from global recipe storage
        if (!removeRecipeFromGlobalRecipes(recipeName)) {
            deletePresenter.prepareFailureView(); // Notify failure if unable to remove globally
            return;
        }

        // Step 4: Sync global recipes data to cloud after modification
        recipeDataAccessObject.deleteFileFromFileIo(); // Delete the old file from the cloud
        recipeDataAccessObject.uploadFileToFileIo();  // Upload the updated file to the cloud

        // If both removals succeed, notify success
        deletePresenter.prepareSuccessView();
    }

    /**
     * Removes a recipe from the user's createdRecipes list in all_users.json.
     *
     * @param username   The username of the user.
     * @param recipeName The name of the recipe to remove.
     * @return true if the recipe is successfully removed; false otherwise.
     */
    private boolean removeRecipeFromUser(String username, String recipeName) {
        boolean result = userDataAccessObject.removeUserCreatedRecipe(username, recipeName);
        if (result) {
            System.out.println("Recipe '" + recipeName + "' successfully removed from user '" + username + "'.");
        } else {
            System.err.println("Failed to remove recipe '" + recipeName + "' from user '" + username + "'.");
        }
        return result;
    }

    /**
     * Removes a recipe from the global recipe storage (all_recipes.json).
     *
     * @param recipeName The name of the recipe to remove.
     * @return true if the recipe is successfully removed; false otherwise.
     */
    private boolean removeRecipeFromGlobalRecipes(String recipeName) {
        // Step 1: Remove the recipe from the local file
        boolean localDeleted = recipeDataAccessObject.removeRecipeFromLocalFile("new_recipes.json", recipeName);
        if (!localDeleted) {
            System.err.println("Failed to remove recipe '" + recipeName + "' from the local file.");
            return false;
        }

        // Step 2: Load recipes from the cloud and attempt removal
        recipeDataAccessObject.loadRecipesFromCloud();
        if (recipeDataAccessObject.isNameInRecipes(recipeName)) {
            recipeDataAccessObject.removeRecipeByName(recipeName);

            // Step 3: Write the updated recipes to local file
            List<Recipe> updatedRecipes = recipeDataAccessObject.getCachedRecipes();
            recipeDataAccessObject.writeRecipesToFile(updatedRecipes);

            System.out.println("Recipe '" + recipeName + "' successfully removed from the cloud.");
            return true;
        } else {
            System.err.println("Recipe '" + recipeName + "' not found in the cloud.");
            return false;
        }
    }
}
