package use_case.favorite_receipe;

/**
 * Represents the output data for the "favorite recipe" use case.
 * This class encapsulates the result of a user's request to manage their favorite recipes,
 * including the username of the current user and the updated list of their favorite recipes.
 * It serves as the data transfer object between the interactor and the presenter.
 */
public class FavoriteRecipeOutputData {

    private final String username;
    private final String[] favoriteRecipes;

    public FavoriteRecipeOutputData(String username, String[] favoriteRecipes) {
        this.username = username;
        this.favoriteRecipes = favoriteRecipes;
    }

    /**
     * Gets the username of the current user.
     *
     * @return the username of the current user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the updated list of the user's favorite recipes.
     *
     * @return an array of favorite recipe names
     */
    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }
}
