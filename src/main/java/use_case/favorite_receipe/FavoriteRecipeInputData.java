package use_case.favorite_receipe;

/**
 * The FavoriteRecipe Data for the FavoriteRecipe Use Case.
 */
public class FavoriteRecipeInputData {
    private final String username;
    private String[] recipeNames;

    public FavoriteRecipeInputData(String username, String[] recipeNames) {
        this.username = username;
        this.recipeNames = recipeNames;
    }

    public String getUsername() {
        return username;
    }

    public String[] getRecipeNames() {
        return recipeNames;
    }

    public void setRecipeNames(String[] recipeNames) {
        this.recipeNames = recipeNames;
    }
}
