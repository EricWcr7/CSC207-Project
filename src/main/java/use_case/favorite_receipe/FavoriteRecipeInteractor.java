package use_case.favorite_receipe;

import data_access.FavoriteRecipeDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.User;

/**
 * The FavoriteRecipe Interactor.
 */
public class FavoriteRecipeInteractor implements FavoriteRecipeInputBoundary {
    private final FavoriteRecipeOutputBoundary favoriteRecipePresenter;
    private final FavoriteRecipeDataAccessObject favoriteRecipeDataAccessObject;
    private final InMemoryUserDataAccessObject inMemoryUserDataAccessObject;

    public FavoriteRecipeInteractor(FavoriteRecipeOutputBoundary favoriteRecipePresenter,
                                    FavoriteRecipeDataAccessObject favoriteRecipeDataAccessObject,
                                    InMemoryUserDataAccessObject inMemoryUserDataAccessObject) {
        this.favoriteRecipePresenter = favoriteRecipePresenter;
        this.favoriteRecipeDataAccessObject = favoriteRecipeDataAccessObject;
        this.inMemoryUserDataAccessObject = inMemoryUserDataAccessObject;
    }

    @Override
    public void execute(FavoriteRecipeInputData favoriteRecipeInputData) {
        final String username = favoriteRecipeInputData.getUsername();
        final String[] favoriteRecipes = favoriteRecipeInputData.getFavoriteRecipes();
        final User user = inMemoryUserDataAccessObject.get(username);
        user.setFavoriteRecipes(favoriteRecipes);
        inMemoryUserDataAccessObject.updateUserFavoriteRecipes(user);
    }
}
