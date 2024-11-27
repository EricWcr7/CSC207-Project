package use_case.shopping_list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingListInteractor implements ShoppingListInputBoundary {
    private final ShoppingListOutputBoundary shoppingListPresenter;
    private final ShoppingListDataAccessInterface shoppingListDataAccessObject;

    public ShoppingListInteractor(ShoppingListOutputBoundary shoppingListPresenter,
                                  ShoppingListDataAccessInterface shoppingListDataAccessObject) {
        this.shoppingListPresenter = shoppingListPresenter;
        this.shoppingListDataAccessObject = shoppingListDataAccessObject;
    }

    @Override
    public void generateShoppingList(ShoppingListInputData inputData) {
        final String username = inputData.getUsername();
        final String[] favoriteRecipes = inputData.getRecipeNames();
        final Map<String, Double> map = new HashMap<>();
        final ShoppingListOutputData shoppingListOutputData = new ShoppingListOutputData(username, favoriteRecipes,
                map);
        shoppingListPresenter.presentShoppingList(shoppingListOutputData);
    }
}