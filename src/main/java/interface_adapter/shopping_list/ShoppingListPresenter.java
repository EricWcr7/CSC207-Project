package interface_adapter.shopping_list;

import use_case.shopping_list.ShoppingListOutputBoundary;
import use_case.shopping_list.ShoppingListOutputData;

/**
 * The ShoppingListPresenter is responsible for receiving output data from the shopping list
 * use case interactor and preparing it for display in the view.
 * This presenter updates the `ShoppingListViewModel` with the data from
 * `ShoppingListOutputData` and triggers notifications to the view to reflect the changes.
 * It also interacts with the `ViewManagerModel` to handle view-related transitions or updates
 * as necessary in the application.
 * In the Clean Architecture framework, this class bridges the application logic and the
 * interface adapter layer (view) without directly handling UI logic.
 */
public class ShoppingListPresenter implements ShoppingListOutputBoundary {

    private final ShoppingListViewModel shoppingListViewModel;

    public ShoppingListPresenter(ShoppingListViewModel shoppingListViewModel) {
        this.shoppingListViewModel = shoppingListViewModel;
    }

    @Override
    public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
        // Logic to present the shopping list view model to the view
        // For example, this could involve notifying the view to display the updated data
        System.out.println("Presenter received ShoppingListOutputData:");
        System.out.println("Username: " + shoppingListOutputData.getUsername());
        System.out.println("Recipe Names: " + shoppingListOutputData.getRecipeNames());
        System.out.println("Ingredients: " + shoppingListOutputData.getIngredients());
        final ShoppingListState currentState = shoppingListViewModel.getState();
        currentState.setUsername(shoppingListOutputData.getUsername());
        currentState.setRecipeNames(shoppingListOutputData.getRecipeNames());
        currentState.setIngredients(shoppingListOutputData.getIngredients());
        shoppingListViewModel.setState(currentState);
        shoppingListViewModel.firePropertyChanged("The Overall Shopping List");
    }
}
