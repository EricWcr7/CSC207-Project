package data_access;

import entity.User;
import use_case.favorite_receipe.FavoriteRecipeDataAccessInterface;

/**
 * Data Access Object (DAO) implementation for managing favorite recipes of a user.
 * This class provides methods to get and set a user's username and their list of favorite recipes.
 * It also provides a method for updating a user's favorite recipes.
 */
public class FavoriteRecipeDataAccessObject implements FavoriteRecipeDataAccessInterface {
    private String username;
    private String[] favoriteRecipes;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setFavoriteRecipes(String[] favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    @Override
    public void updateUserFavoriteRecipes(User user) {

    }

}
