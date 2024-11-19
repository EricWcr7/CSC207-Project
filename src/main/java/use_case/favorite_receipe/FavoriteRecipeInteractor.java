package use_case.favorite_receipe;

import data_access.RecipeDataAccessObject;
import use_case.recipe_search.RecipeSearchInputBoundary;
import use_case.recipe_search.RecipeSearchOutputBoundary;

public class FavoriteRecipeInteractor implements FavoriteRecipeInputBoundary {
    private final FavoriteRecipeOutputBoundary favoriteRecipePresenter;

    public FavoriteRecipeInteractor(FavoriteRecipeOutputBoundary favoriteRecipePresenter) {
        this.favoriteRecipePresenter = favoriteRecipePresenter;
    }

    @Override
    public void execute(FavoriteRecipeInputData favoriteRecipeInputData) {

    }
}
