package use_case.delete;

/**
 * The data access interface for deleting recipes from all_users.json.
 */
public interface DeleteUserDataAccessInterface {

    /**
     * Deletes a recipe created by a specific user from all_users.json.
     *
     * @param recipeName The name of the recipe to delete.
     * @throws Exception if an error occurs during deletion.
     */
    void deleteRecipeForUser(String recipeName);
}
