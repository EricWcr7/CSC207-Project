package data_access;

import entity.User;
import use_case.favorite_receipe.FavoriteRecipeDataAccessInterface;
import use_case.favorite_receipe.FavoriteRecipeInteractor;

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
