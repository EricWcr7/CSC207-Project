package interface_adapter.favorite_recipe;

import interface_adapter.ViewManagerModel;
import use_case.favorite_receipe.FavoriteRecipeOutputBoundary;
import use_case.favorite_receipe.FavoriteRecipeOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class FavoriteRecipePresenter implements FavoriteRecipeOutputBoundary {
    private final FavoriteRecipeViewModel favoriteRecipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public FavoriteRecipePresenter(ViewManagerModel viewManagerModel,
                                   FavoriteRecipeViewModel favoriteRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.favoriteRecipeViewModel = favoriteRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(FavoriteRecipeOutputData outputData) {
    }

    @Override
    public void prepareFailureView(String errorMessage) {
    }
}
