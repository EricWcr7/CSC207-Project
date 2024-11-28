package interface_adapter.delete;

import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInputData;

/**
 * Controller class for the Delete Use Case.
 * Acts as the intermediary between the user interface and the use case interactor.
 * It receives user inputs, validates them, and passes them to the interactor
 * through the input boundary for processing.
 */
public class DeleteController {

    // The input boundary for the Delete Use Case, which handles the deletion logic.
    private final DeleteInputBoundary deleteInputBoundary;

    /**
     * Constructor for the DeleteController.
     * Initializes the controller with the given input boundary.
     *
     * @param deleteInputBoundary the input boundary that defines the entry point for
     *                            the Delete Use Case logic.
     */
    public DeleteController(DeleteInputBoundary deleteInputBoundary) {
        this.deleteInputBoundary = deleteInputBoundary;
    }

    /**
     * Deletes a recipe with the specified name.
     * This method validates the input recipe name and converts it into the
     * required input data format before invoking the delete use case logic.
     *
     * @param recipeName the name of the recipe to be deleted.
     * @throws IllegalArgumentException if the recipe name is null or empty.
     */
    public void deleteRecipe(String recipeName) {
        // Validate the recipe name to ensure it is not null or empty.
        if (recipeName == null || recipeName.isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be null or empty.");
        }

        // Create an instance of DeleteInputData with the validated recipe name.
        final DeleteInputData inputData = new DeleteInputData(recipeName);

        // Pass the input data to the input boundary to execute the delete use case.
        deleteInputBoundary.execute(inputData);
    }
}

