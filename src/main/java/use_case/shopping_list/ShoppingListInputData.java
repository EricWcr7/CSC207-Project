package use_case.shopping_list;

/**
 * Represents the input data for the ShoppingList Use Case.
 * This class encapsulates the information required to generate a shopping list,
 * including the names of the recipes and the username of the user requesting the list.
 * It serves as a data transfer object between the controller and the interactor.
 */
public class ShoppingListInputData {
    private final String[] recipeNames;
    private final String username;

    public ShoppingListInputData(String username, String[] recipeNames) {
        this.recipeNames = recipeNames;
        this.username = username;
    }

    /**
     * Gets the names of the recipes for which the shopping list is to be generated.
     *
     * @return an array of recipe names
     */
    public String[] getRecipeNames() {
        return recipeNames;
    }

    /**
     * Gets the username of the user requesting the shopping list.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }
}

