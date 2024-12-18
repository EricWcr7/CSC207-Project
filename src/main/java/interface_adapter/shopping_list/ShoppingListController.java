package interface_adapter.shopping_list;

import use_case.shopping_list.ShoppingListInputBoundary;

/**
 * The ShoppingListController is responsible for handling user input related to
 * generating a shopping list. It acts as a bridge between the UI layer and the
 * application use case layer in the Clean Architecture framework.
 * The controller converts raw user input (e.g., username and recipe names) into
 * a structured format (`ShoppingListInputData`) and delegates the request to
 * the `ShoppingListInteractor` through the `ShoppingListInputBoundary` interface.
 */
public class ShoppingListController {
    private final ShoppingListInputBoundary shoppingListInteractor;

    public ShoppingListController(ShoppingListInputBoundary shoppingListInteractor) {
        this.shoppingListInteractor = shoppingListInteractor;
    }

    /**
     * Handles the execution of the shopping list generation process.
     * This method takes the username and the list of recipe names as input, wraps
     * them in a `ShoppingListInputData` object, and passes it to the interactor
     * for processing.
     */
    public void execute() {
        shoppingListInteractor.execute();
    }
}
