package interface_adapter.delete;

import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInputData;

public class DeleteController {
    private final DeleteInputBoundary deleteInputBoundary;

    public DeleteController(DeleteInputBoundary deleteInputBoundary) {
        this.deleteInputBoundary = deleteInputBoundary;
    }


    /**
     * Deletes a recipe from all_recipes.json based on the recipe name.
     *
     * @param recipeName The name of the recipe to delete.
     */
    public void deleteRecipeFromAllRecipes(String recipeName) {
        DeleteInputData inputData = new DeleteInputData(recipeName);
        deleteInputBoundary.deleteRecipe(inputData);
    }

    /**
     * Deletes a recipe from all_users.json for the current user based on the recipe name.
     *
     * @param recipeName The name of the recipe to delete.
     */
    public void deleteRecipeFromUserCreatedRecipes(String recipeName) {
        DeleteInputData inputData = new DeleteInputData(recipeName);
        deleteInputBoundary.deleteUserRecipe(inputData);
    }

    /**
     * Gets the current user's name from the session.
     *
     * @return The name of the currently logged-in user.
     */
    private String getCurrentUserName() {
        return util.Session.getCurrentUser().getName();
    }
}
