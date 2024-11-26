package use_case.shopping_list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingListInteractor implements ShoppingListInputBoundary {
    private final ShoppingListOutputBoundary shoppingListPresenter;

    public ShoppingListInteractor(ShoppingListOutputBoundary shoppingListPresenter) {
        this.shoppingListPresenter = shoppingListPresenter;
    }

    @Override
    public void generateShoppingList(ShoppingListInputData inputData) {
        final String username = inputData.getUsername();
        final List<String> favoriteRecipes = inputData.getRecipeNames();
        final Map<String, Double> map = new HashMap<>();
        final ShoppingListOutputData shoppingListOutputData = new ShoppingListOutputData(username, favoriteRecipes, map);
        shoppingListPresenter.presentShoppingList(shoppingListOutputData);
    }
}