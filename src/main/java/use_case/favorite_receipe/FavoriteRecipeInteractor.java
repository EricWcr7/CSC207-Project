package use_case.favorite_receipe;

/**
 * The FavoriteRecipe Interactor.
 */
public class FavoriteRecipeInteractor implements FavoriteRecipeInputBoundary {
    private final FavoriteRecipeOutputBoundary favoriteRecipePresenter;

    public FavoriteRecipeInteractor(FavoriteRecipeOutputBoundary favoriteRecipePresenter) {
        this.favoriteRecipePresenter = favoriteRecipePresenter;
    }

    @Override
    public void execute(FavoriteRecipeInputData favoriteRecipeInputData) {

    }
}
