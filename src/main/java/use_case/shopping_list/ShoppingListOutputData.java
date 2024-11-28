package use_case.shopping_list;

import java.util.Map;

/**
 * Represents the output data for the ShoppingList Use Case.
 * This class encapsulates the results of generating a shopping list, including the username
 * of the user, the names of the recipes included in the shopping list, and a map of ingredients
 * with their corresponding quantities or measurements. It serves as a data transfer object
 * between the interactor and the presenter.
 */
public class ShoppingListOutputData {
    private final String username;
    private final String[] recipeNames;
    private final Map<String, String> ingredients;

    public ShoppingListOutputData(String username, String[] recipeNames, Map<String, String> ingredients) {
        this.username = username;
        this.recipeNames = recipeNames;
        this.ingredients = ingredients;
    }

    /**
     * Gets the username of the user requesting the shopping list.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the names of the recipes included in the shopping list.
     *
     * @return an array of recipe names
     */
    public String[] getRecipeNames() {
        return recipeNames;
    }

    /**
     * Gets the ingredients required for the recipes in the shopping list.
     *
     * @return a map of ingredient names to their quantities or measurements
     */
    public Map<String, String> getIngredients() {
        return ingredients;
    }
}
