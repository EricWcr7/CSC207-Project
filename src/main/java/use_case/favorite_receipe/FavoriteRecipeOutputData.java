package use_case.favorite_receipe;

import entity.Recipe;

import java.util.List;

/**
 * Output Data for the FavoriteRecipe Use Case.
 */
public class FavoriteRecipeOutputData {

    private final String username;
    private final String[] favoriteRecipes;

    public FavoriteRecipeOutputData(String username, String[] favoriteRecipes) {
        this.username = username;
        this.favoriteRecipes = favoriteRecipes;
    }

    public String getUsername() {
        return username;
    }

    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }
}
