package use_case.recipe_search;

/**
 * Represents the input data for the RecipeSearch Use Case.
 * This class encapsulates the data required to perform a recipe search, including
 * the search keyword, the username of the user performing the search, and the user's
 * favorite recipes. It serves as a data transfer object between the controller and
 * the interactor.
 */
public class RecipeSearchInputData {
    private final String searchKeyword;
    private final String username;
    private String[] favoriteRecipes;

    public RecipeSearchInputData(String searchKeyword, String username, String[] favoriteRecipes) {
        this.searchKeyword = searchKeyword;
        this.username = username;
        this.favoriteRecipes = favoriteRecipes;
    }

    /**
     * Gets the search keyword used for finding recipes.
     *
     * @return the search keyword
     */
    public String getSearchKeyword() {
        return searchKeyword;
    }

    /**
     * Gets the username of the user performing the recipe search.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the user's favorite recipes.
     *
     * @return an array of favorite recipes
     */
    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }

    /**
     * Sets the user's favorite recipes.
     *
     * @param favoriteRecipes an array of the updated favorite recipes
     */
    public void setFavoriteRecipes(String[] favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }
}
