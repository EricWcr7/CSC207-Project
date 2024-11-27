package interface_adapter.shopping_list;

import use_case.shopping_list.ShoppingListInputBoundary;
import use_case.shopping_list.ShoppingListInputData;

public class ShoppingListController {
    private final ShoppingListInputBoundary shoppingListInteractor;

    public ShoppingListController(ShoppingListInputBoundary shoppingListInteractor) {
        this.shoppingListInteractor = shoppingListInteractor;
    }

    public void execute(String username, String[] recipeNames) {
        ShoppingListInputData inputData = new ShoppingListInputData(username, recipeNames);
        shoppingListInteractor.execute(inputData);
    }
}

// Assume FavoriteRecipeRepository is an interface for fetching favorite recipes and their ingredients

