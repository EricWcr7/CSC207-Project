package interface_adapter.shopping_list;

import use_case.shopping_list.ShoppingListInputBoundary;
import use_case.shopping_list.ShoppingListInputData;

import java.util.List;

public class ShoppingListController {
    private final ShoppingListInputBoundary inputBoundary;

    public ShoppingListController(ShoppingListInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleGenerateShoppingList(List<String> recipeNames) {
        ShoppingListInputData inputData = new ShoppingListInputData(recipeNames);
        inputBoundary.generateShoppingList(inputData);
    }
}

// Assume FavoriteRecipeRepository is an interface for fetching favorite recipes and their ingredients

