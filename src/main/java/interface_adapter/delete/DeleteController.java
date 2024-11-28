package interface_adapter.delete;

import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInputData;

/**
 * The DeleteController class acts as the intermediary between the user interface
 * and the business logic of the delete use case. It processes input from the view
 * layer, validates it, and delegates the execution to the appropriate use case interactor.
 */
public class DeleteController {

    /**
     * A reference to the DeleteInputBoundary interface, allowing the controller
     * to delegate the delete operation to the interactor.
     */
    private final DeleteInputBoundary deleteInputBoundary;

    /**
     * Constructs a DeleteController with the specified DeleteInputBoundary.
     *
     * @param deleteInputBoundary the input boundary to handle the delete use case.
     *                            It provides the logic to perform the delete operation.
     */
    public DeleteController(DeleteInputBoundary deleteInputBoundary) {
        this.deleteInputBoundary = deleteInputBoundary;
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
