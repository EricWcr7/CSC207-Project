package use_case.like_and_dislike_a_recipe;

/**
 * Data Access Object (DAO) interface for managing user interactions related to liking and disliking recipes.
 * This interface provides methods for accessing and updating user-specific data, such as liked and disliked recipes.
 */
public interface UserLikeAndDislikeDataAccessInterface {

    /**
     * Retrieves the username of the current user.
     * @return a {@code String} representing the current username.
     */
    String getCurrentUsername();

    /**
     * Checks if the current user has liked a specific recipe.
     * @param recipeName the name of the recipe to check.
     * @return {@code true} if the user has liked the recipe, {@code false} otherwise.
     */
    boolean hasUserLikedRecipe(String recipeName);

    /**
     * Adds a recipe to the list of recipes liked by the current user.
     * @param recipeName the name of the recipe to add to the user's liked list.
     */
    void addLikedRecipe(String recipeName);

    /**
     * Checks if the current user has disliked a specific recipe.
     * @param recipeName the name of the recipe to check.
     * @return {@code true} if the user has disliked the recipe, {@code false} otherwise.
     */
    boolean hasUserDislikedRecipe(String recipeName);

    /**
     * Adds a recipe to the list of recipes disliked by the current user.
     * @param recipeName the name of the recipe to add to the user's disliked list.
     */
    void addDislikedRecipe(String recipeName);

    /**
     * Updates the user's data to reflect that they have liked a specific recipe.
     * This method ensures persistence or synchronization of the user's liked recipes with the data source.
     * @param recipeName the name of the recipe to update in the user's liked list.
     */
    void updateUserLikedRecipe(String recipeName);

    /**
     * Updates the user's data to reflect that they have disliked a specific recipe.
     * This method ensures persistence or synchronization of the user's disliked recipes with the data source.
     * @param recipeName the name of the recipe to update in the user's disliked list.
     */
    void updateUserDislikedRecipe(String recipeName);
}
