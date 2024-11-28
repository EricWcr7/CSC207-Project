package use_case.recipe_search;

import java.util.List;

import entity.Recipe;

/**
 * Output Data for the RecipeSearch Use Case.
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

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public String getUsername() {
        return username;
    }

    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }
}

