package use_case.favorite_receipe;

/**
 * DAO for the FavoriteRecipe Use Case.
 */
public interface FavoriteRecipeDataAccessInterface {

    public String getUsername();

    public String[] getFavoriteRecipes();

    public void setUsername(String username);

    public void setFavoriteRecipes(String[] favoriteRecipes);
}
