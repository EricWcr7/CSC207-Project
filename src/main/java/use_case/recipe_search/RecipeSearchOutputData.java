package use_case.recipe_search;

import java.util.List;

import entity.Recipe;

/**
 * Represents the output data for the RecipeSearch Use Case.
 * This class encapsulates the results of a recipe search, including the search keyword,
 * the list of matching recipes, the username of the user who performed the search, and
 * the user's favorite recipes. It serves as a data transfer object between the interactor
 * and the presenter.
 */
public class RecipeSearchOutputData {
    private final String searchKeyword;
    private final List<Recipe> recipes;
    private final String username;
    private final String[] favoriteRecipes;

    public RecipeSearchOutputData(String searchKeyword,
                                  List<Recipe> recipes,
                                  String username,
                                  String[] favoriteRecipes) {
        this.searchKeyword = searchKeyword;
        this.recipes = recipes;
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
     * Gets the list of recipes matching the search keyword.
     *
     * @return a list of matching recipes
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Gets the username of the user who performed the recipe search.
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
}

