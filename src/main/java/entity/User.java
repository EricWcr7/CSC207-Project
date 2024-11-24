package entity;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Add created Recipe.
     * @param recipe the created recipe
     */
    void addCreatedRecipe(Recipe recipe);

    /**
     * Returns the favoriteRecipes of the user.
     * @return the favoriteRecipes of the user.
     */
    String[] getFavoriteRecipes();

    /**
     * Set new favoriteRecipe.
     * @param favoriteRecipes the new favoriteRecipes;
     */
    void setFavoriteRecipes(String[] favoriteRecipes);
}
