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

    /**
     * Checks whether a user has already liked a specific recipe.
     * @param recipeName the recipe name
     * @return true if the user has liked the recipe before; false otherwise
     */
    boolean hasUserLikedRecipe(String recipeName);

    /**
     * Checks whether the user has disliked a specific recipe.
     *
     * @param recipeName the name of the recipe to check.
     * @return {@code true} if the user has disliked the recipe, {@code false} otherwise.
     */
    boolean hasUserDislikedRecipe(String recipeName);

    /**
     * Adds a recipe to the user's list of liked recipes.
     *
     * @param recipeName the name of the recipe to add to the liked recipes list.
     */
    void addLikedRecipe(String recipeName);

    /**
     * Adds a recipe to the user's list of disliked recipes.
     *
     * @param recipeName the name of the recipe to add to the disliked recipes list.
     */
    void addDislikedRecipe(String recipeName);

}
