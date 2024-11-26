package interface_adapter.shopping_list;

import use_case.shopping_list.ShoppingListOutputBoundary;

public class ShoppingListPresenter implements ShoppingListOutputBoundary {
    @Override
    public void presentShoppingList(ShoppingListViewModel shoppingListViewModel) {
        // Logic to present the shopping list view model to the view
        // For example, this could involve notifying the view to display the updated data
    }
}
