package use_case.delete;

/**
 * The data access interface for deleting recipes from all_users.json.
 */
public interface DeleteUserDataAccessInterface {

    /**
     * Deletes a recipe created by a specific user from all_users.json.
     *
     * @param username   The username of the user who created the recipe.
     * @param recipeName The name of the recipe to delete.
     * @throws Exception if an error occurs during deletion.
     */
    void deleteRecipeForUser(String username, String recipeName) throws Exception;
}
