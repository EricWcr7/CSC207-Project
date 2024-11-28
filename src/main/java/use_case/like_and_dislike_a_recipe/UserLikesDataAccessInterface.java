package use_case.like_and_dislike_a_recipe;

/**
 * WE DO NOT USE THIS ANYMORE!!!
 * Interface for accessing user like data in the data store.
 * This interface defines methods to check if a user has liked a recipe
 * and to add a new like record for a user.
 */
public interface UserLikesDataAccessInterface {
    /**
     * Checks whether a user has already liked a specific recipe.
     *
     * @param userName   the name of the user
     * @param recipeName the name of the recipe
     * @return true if the user has liked the recipe before; false otherwise
     */
    boolean hasUserLikedRecipe(String userName, String recipeName);

    /**
     * Adds a like record indicating that a user has liked a specific recipe.
     *
     * @param userName   the name of the user
     * @param recipeName the name of the recipe
     */
    void addUserLike(String userName, String recipeName);

}
