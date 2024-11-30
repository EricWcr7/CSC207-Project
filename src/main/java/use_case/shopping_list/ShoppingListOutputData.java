package use_case.shopping_list;

import java.util.Set;

/**
 * Represents the output data for the ShoppingList Use Case.
 * This class encapsulates the results of generating a shopping list, including the username
 * of the user, the names of the recipes included in the shopping list, and a set of unique ingredient names.
 * It serves as a data transfer object between the interactor and the presenter.
 */
public class ShoppingListOutputData {
    private final String username;
    private final String[] recipeNames;
    private final Set<String> ingredients;

    public ShoppingListOutputData(String username, String[] recipeNames, Set<String> ingredients) {
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
     * Gets the unique ingredients required for the recipes in the shopping list.
     *
     * @return a set of unique ingredient names
     */
    public Set<String> getIngredients() {
        return ingredients;
    }
}

