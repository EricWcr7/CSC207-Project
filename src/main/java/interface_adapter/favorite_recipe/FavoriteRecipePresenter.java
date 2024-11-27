package interface_adapter.favorite_recipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.shopping_list.ShoppingListState;
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
    public void prepareSuccessView(FavoriteRecipeOutputData outputData) {
    }

    @Override
    public void prepareFailureView(String errorMessage) {
    }

    @Override
    public void switchToShoppingListView(FavoriteRecipeOutputData outputData) {
        // Check and log the current state
        // if (favoriteRecipeViewModel.getState() == null) {
        //     System.out.println("FavoriteRecipeState is null, initializing default state.");
        //     favoriteRecipeViewModel.setState(new FavoriteRecipeState());
        // } else {
        //     System.out.println("Switching to Shopping List View, current state: " + favoriteRecipeViewModel.getState());
        // }

        // Switch to the shopping list view
        final ShoppingListState currentState = shoppingListViewModel.getState();
        currentState.setUsername(outputData.getUsername());
        currentState.setRecipeNames(outputData.getFavoriteRecipes());
        shoppingListViewModel.setState(currentState);
        shoppingListViewModel.firePropertyChanged();
        viewManagerModel.setState(shoppingListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
