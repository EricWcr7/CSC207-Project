package use_case.delete;

/**
 * Data transfer object for the Delete Use Case.
 * Represents the input data required to delete a recipe.
 */
public class DeleteInputData {

    // The name of the recipe to be deleted
    private final String recipeName;

    /**
     * Constructor for DeleteInputData.
     *
     * @param recipeName The name of the recipe to be deleted.
     *                   Must not be null or empty.
     * @throws IllegalArgumentException If the recipe name is null or empty.
     */
    public DeleteInputData(String recipeName) {
        // Validate that the recipe name is not null or empty
        if (recipeName == null || recipeName.isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be null or empty.");
        }
        this.recipeName = recipeName; // Assign the validated recipe name
    }

    /**
     * Retrieves the name of the recipe to be deleted.
     *
     * @return The name of the recipe as a string.
     */
    public String getRecipeName() {
        return recipeName;
    }
}
