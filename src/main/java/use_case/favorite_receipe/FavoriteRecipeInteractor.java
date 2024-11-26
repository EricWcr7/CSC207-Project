package use_case.favorite_receipe;

import data_access.FavoriteRecipeDataAccessObject;

/**
 * The FavoriteRecipe Interactor.
 */
public class FavoriteRecipeInteractor implements FavoriteRecipeInputBoundary {
    private final FavoriteRecipeOutputBoundary favoriteRecipePresenter;
    private final FavoriteRecipeDataAccessObject favoriteRecipeDataAccessObject;

    public FavoriteRecipeInteractor(FavoriteRecipeOutputBoundary favoriteRecipePresenter,
                                    FavoriteRecipeDataAccessObject favoriteRecipeDataAccessObject) {
        this.favoriteRecipePresenter = favoriteRecipePresenter;
        this.favoriteRecipeDataAccessObject = favoriteRecipeDataAccessObject;
    }

    @Override
    public void execute(FavoriteRecipeInputData favoriteRecipeInputData) {
        final String username = favoriteRecipeInputData.getUsername();
        final String[] favoriteRecipes = favoriteRecipeInputData.getFavoriteRecipes();
        favoriteRecipeDataAccessObject.setUsername(username);
        favoriteRecipeDataAccessObject.setFavoriteRecipes(favoriteRecipes);
    }
}
