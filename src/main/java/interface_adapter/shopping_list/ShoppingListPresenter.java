package interface_adapter.shopping_list;

import interface_adapter.ViewManagerModel;
import use_case.shopping_list.ShoppingListOutputBoundary;
import use_case.shopping_list.ShoppingListOutputData;

public class ShoppingListPresenter implements ShoppingListOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ShoppingListViewModel shoppingListViewModel;

    public ShoppingListPresenter(ViewManagerModel viewManagerModel,
                                 ShoppingListViewModel shoppingListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.shoppingListViewModel = shoppingListViewModel;
    }

    @Override
    public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
        // Logic to present the shopping list view model to the view
        // For example, this could involve notifying the view to display the updated data
        final ShoppingListState currentState = shoppingListViewModel.getState();
        currentState.setUsername(shoppingListOutputData.getUsername());
        currentState.setRecipeNames(shoppingListOutputData.getRecipeNames());
        currentState.setIngredients(shoppingListOutputData.getIngredients());
        shoppingListViewModel.setState(currentState);
        shoppingListViewModel.firePropertyChanged("The Overall Shopping List");
        viewManagerModel.setState(shoppingListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
