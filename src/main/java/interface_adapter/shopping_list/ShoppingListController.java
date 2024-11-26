package interface_adapter.shopping_list;

import use_case.shopping_list.ShoppingListInputBoundary;
import use_case.shopping_list.ShoppingListInputData;

import java.util.List;

public class ShoppingListController {
    private final ShoppingListInputBoundary shoppingListInteractor;

    public ShoppingListController(ShoppingListInputBoundary shoppingListInteractor) {
        this.shoppingListInteractor = shoppingListInteractor;
    }

    public void handleGenerateShoppingList(List<String> recipeNames) {
        ShoppingListInputData inputData = new ShoppingListInputData(recipeNames);
        shoppingListInteractor.generateShoppingList(inputData);
    }
}

// Assume FavoriteRecipeRepository is an interface for fetching favorite recipes and their ingredients

