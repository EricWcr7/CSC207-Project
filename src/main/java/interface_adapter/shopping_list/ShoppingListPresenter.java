package interface_adapter.shopping_list;

import interface_adapter.ViewManagerModel;
import use_case.shopping_list.ShoppingListOutputBoundary;

public class ShoppingListPresenter implements ShoppingListOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ShoppingListViewModel shoppingListViewModel;

    public ShoppingListPresenter(ViewManagerModel viewManagerModel,
                                 ShoppingListViewModel shoppingListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.shoppingListViewModel = shoppingListViewModel;
    }
    @Override
    public void presentShoppingList(ShoppingListViewModel shoppingListViewModel) {
        // Logic to present the shopping list view model to the view
        // For example, this could involve notifying the view to display the updated data
    }
}
