package interface_adapter.favorite_recipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.shopping_list.ShoppingListViewModel;
import use_case.favorite_receipe.FavoriteRecipeOutputBoundary;
import use_case.favorite_receipe.FavoriteRecipeOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class FavoriteRecipePresenter implements FavoriteRecipeOutputBoundary {
    private final FavoriteRecipeViewModel favoriteRecipeViewModel;
    private final ShoppingListViewModel shoppingListViewModel;
    private final ViewManagerModel viewManagerModel;

    public FavoriteRecipePresenter(ViewManagerModel viewManagerModel,
                                   FavoriteRecipeViewModel favoriteRecipeViewModel,
                                   ShoppingListViewModel shoppingListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.favoriteRecipeViewModel = favoriteRecipeViewModel;
        this.shoppingListViewModel = shoppingListViewModel;
    }

    @Override
    public void updateFavoriteRecipe(FavoriteRecipeOutputData outputData) {
        final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
        currentState.setUsername(outputData.getUsername());
        currentState.setFavoriteRecipes(outputData.getFavoriteRecipes());
        favoriteRecipeViewModel.setState(currentState);
        favoriteRecipeViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView(String errorMessage) {
    }

    @Override
    public void switchToShoppingListView() {
        viewManagerModel.setState(shoppingListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
