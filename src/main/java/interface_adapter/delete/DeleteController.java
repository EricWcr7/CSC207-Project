package interface_adapter.delete;

import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInputData;

public class DeleteController {
    private final DeleteInputBoundary deleteInputBoundary;

    public DeleteController(DeleteInputBoundary deleteInputBoundary) {
        this.deleteInputBoundary = deleteInputBoundary;
    }


//    /**
//     * Deletes a recipe from all_recipes.json based on the recipe name.
//     *
//     * @param recipeName The name of the recipe to delete.
//     */
//    public void deleteRecipeFromAllRecipes(String recipeName) {
//        DeleteInputData inputData = new DeleteInputData(recipeName);
//        deleteInputBoundary.deleteRecipe(inputData);
//    }

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
     * Deletes a recipe with the given name by validating the input and delegating
     * the task to the use case interactor.
     *
     * @param recipeName the name of the recipe to be deleted. Must not be null or empty.
     * @throws IllegalArgumentException if the recipe name is null or empty.
     */
    public void deleteRecipe(String recipeName) {
        // Validate input to ensure the recipe name is not null or empty
        if (recipeName == null || recipeName.isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be null or empty.");
        }

        // Wrap the recipe name in a DeleteInputData object to pass to the interactor
        DeleteInputData inputData = new DeleteInputData(recipeName);

        // Delegate the execution to the interactor through the input boundary
        deleteInputBoundary.execute(inputData);
    }
}
