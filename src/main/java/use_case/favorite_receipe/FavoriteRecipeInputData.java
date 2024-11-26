package use_case.favorite_receipe;

/**
 * The FavoriteRecipe Data for the Login Use Case.
 */
public class FavoriteRecipeInputData {
    private final String username;
    private final String[] favoriteRecipes;

    public FavoriteRecipeInputData(String username, String[] favoriteRecipes) {
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
