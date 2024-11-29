package use_case.create_recipe;

import entity.Recipe;

/**
 * The CreateUserDataAccessInterface defines the contract for managing user-created recipes
 * in the data access layer. It provides methods for storing and managing recipes created by the user.
 */
public interface CreateRecipeUserDataAccessInterface {

    /**
     * Adds a newly created recipe to the data storage.
     *
     * @param recipe the Recipe object representing the recipe to be added.
     */
    void addCreatedRecipe(Recipe recipe);
}
