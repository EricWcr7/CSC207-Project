package use_case.delete;

/**
 * Interface for data access operations related to deleting user data.
 * Provides methods for interacting with the all_users.json file and syncing with the cloud.
 */
public interface DUserDataAccessInterface {

    /**
     * Removes a recipe created by the user from the user's recipeCreated list in all_users.json.
     *
     * @param username   The username of the user.
     * @param recipeName The name of the recipe to remove.
     * @return true if the recipe was successfully removed; false otherwise.
     */
    boolean removeUserCreatedRecipe(String username, String recipeName);

    /**
     * Synchronizes the updated all_users.json file with the cloud.
     */
    void syncUsersToCloud();

    /**
     * Loads the current all_users.json data from the cloud into memory.
     */
    void loadUsersFromCloud();
}
