package use_case.shopping_list;

/**
 * The output boundary for the ShoppingList Use Case.
 */
public interface ShoppingListOutputBoundary {

    /**
     * Prepares the success view for the ShoppingList Use Case.
     * @param shoppingListOutputData the output data
     */
    void presentShoppingList(ShoppingListOutputData shoppingListOutputData);
}

