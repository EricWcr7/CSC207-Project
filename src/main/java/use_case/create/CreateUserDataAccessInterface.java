package use_case.create;

import entity.Recipe;

/**
 * Interface for managing user-created recipes in the data access layer.
 * This interface provides a method to add a recipe created by a user to their data. It ensures
 * that the created recipes are properly stored and associated with the respective user.
 * Implementations of this interface should handle the persistence of created recipes.
 */
public interface CreateUserDataAccessInterface {

    /**
     * Adds a created recipe to the user's list of created recipes.
     * This method associates the provided {@link Recipe} object with the current user, indicating
     * that the user has created this recipe. The recipe is added to the user's data for tracking
     * purposes.
     *
     * @param recipe the {@link Recipe} object to be added to the user's list of created recipes
     */
    void addCreatedRecipe(Recipe recipe);
}
