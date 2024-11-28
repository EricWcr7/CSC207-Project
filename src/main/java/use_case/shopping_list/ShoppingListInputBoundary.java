package use_case.shopping_list;

/**
 * Input Boundary for actions which are related to ShoppingList.
 */
public interface ShoppingListInputBoundary {

    /**
     * Executes the ShoppingList use case.
     * @param inputData the input data
     */
    void execute(ShoppingListInputData inputData);
}

