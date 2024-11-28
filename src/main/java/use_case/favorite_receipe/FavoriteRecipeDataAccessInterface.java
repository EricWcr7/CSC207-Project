package use_case.favorite_receipe;

import entity.User;

/**
 * DAO for the FavoriteRecipe Use Case.
 */
public interface FavoriteRecipeDataAccessInterface {

    /**
     * Return the Username.
     * @return Username when this method execute.
     */
    String getUsername();

    /**
     * Return the FavoriteRecipes.
     * @return FavoriteRecipes when this method execute.
     */
    String[] getFavoriteRecipes();

    /**
     * Set the username.
     * @param username the username.
     */
    void setUsername(String username);

    /**
     * Set the favoriteRecipes.
     * @param favoriteRecipes the favoriteRecipes.
     */
    void setFavoriteRecipes(String[] favoriteRecipes);

    /**
     * Update the user.
     * @param user the user.
     */
    void updateUserFavoriteRecipes(User user);

    /**
     * Return the user.
     * @param username the name of user.
     * @return User when this method execute.
     */
    User get(String username);
}
