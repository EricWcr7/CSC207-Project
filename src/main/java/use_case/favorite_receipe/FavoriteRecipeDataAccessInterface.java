package use_case.favorite_receipe;

import entity.User;

/**
 * Interface for the Data Access Object (DAO) in the Favorite Recipe use case.
 * This interface defines the methods required for managing and accessing user-specific
 * favorite recipes in the data storage.
 */
public interface FavoriteRecipeDataAccessInterface {

    /**
     * Retrieves the username associated with the favorite recipes.
     *
     * @return the username as a {@code String}.
     */
    String getUsername();

    /**
     * Retrieves the list of favorite recipes for the user.
     *
     * @return an array of favorite recipes as {@code String[]}.
     */
    String[] getFavoriteRecipes();

    /**
     * Sets the username for the favorite recipes.
     *
     * @param username the username to associate with the favorite recipes.
     */
    void setUsername(String username);

    /**
     * Sets the list of favorite recipes for the user.
     *
     * @param favoriteRecipes an array of favorite recipes to associate with the user.
     */
    void setFavoriteRecipes(String[] favoriteRecipes);

    /**
     * Updates the user's favorite recipes in the data storage.
     * This method is intended to persist any changes made to the user's favorite recipes.
     *
     * @param user the {@link User} entity whose favorite recipes need to be updated.
     */
    void updateUserFavoriteRecipes(User user);
}
