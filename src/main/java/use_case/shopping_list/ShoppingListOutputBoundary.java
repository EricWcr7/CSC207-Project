package use_case.shopping_list;

import interface_adapter.shopping_list.ShoppingListViewModel;

public interface ShoppingListOutputBoundary {
    void presentShoppingList(ShoppingListOutputData shoppingListOutputData);
}

