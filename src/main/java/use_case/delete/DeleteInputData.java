package use_case.delete;

/**
 * Data transfer object for the Delete Use Case.
 * Represents the input data required to delete a recipe.
 */
public class DeleteInputData {

    // The name of the recipe to be deleted
    private final String recipeName;

    // The username of the user who created the recipe
    private final String username;

    /**
     * Constructor for DeleteInputData.
     *
     * @param username   The username of the user who created the recipe.
     * @param recipeName The name of the recipe to be deleted.
     * @throws IllegalArgumentException If the username or recipe name is null or empty.
     */
    public DeleteInputData(String username, String recipeName) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (recipeName == null || recipeName.isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be null or empty.");
        }
        this.username = username;
        this.recipeName = recipeName;
    }

    public String getUsername() {
        return username;
    }

    public String getRecipeName() {
        return recipeName;
    }
}
