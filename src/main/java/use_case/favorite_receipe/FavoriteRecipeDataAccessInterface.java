package use_case.favorite_receipe;

import entity.User;

/**
 * DAO for the FavoriteRecipe Use Case.
 */
public interface FavoriteRecipeDataAccessInterface {

    public String getUsername();

    public String[] getFavoriteRecipes();

    public void setUsername(String username);

    public void setFavoriteRecipes(String[] favoriteRecipes);

    public void updateUserFavoriteRecipes(User user);

    User get(String username);
}
