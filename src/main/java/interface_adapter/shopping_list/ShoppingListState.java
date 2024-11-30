package interface_adapter.shopping_list;

import java.util.Set;

/**
 * The ShoppingListState class serves as a container for the current state of the shopping list.
 * It stores data such as the username, the names of the selected recipes, and the unique ingredients
 * for the shopping list. This class acts as a model for the shopping list's state in the
 * interface adapter layer, providing getters and setters to modify or retrieve the state.
 * In Clean Architecture, this class helps decouple the view from the underlying business logic
 * by maintaining a separate state representation that can be updated and observed.
 */
public class ShoppingListState {
    private String username;
    private String[] recipeNames;
    private Set<String> ingredients;

    /**
     * Updates the list of recipe names for the current shopping list.
     *
     * @param recipeNames an array of recipe names included in the shopping list
     */
    public void setRecipeNames(String[] recipeNames) {
        this.recipeNames = recipeNames;
    }

    /**
     * Updates the set of unique ingredients required for the shopping list.
     *
     * @param ingredients a set of ingredient names included in the shopping list
     */
    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Updates the username associated with the shopping list.
     *
     * @param username the username of the user requesting the shopping list
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the list of recipe names included in the shopping list.
     *
     * @return an array of recipe names
     */
    public String[] getRecipeNames() {
        return recipeNames;
    }

    /**
     * Retrieves the set of unique ingredients required for the shopping list.
     *
     * @return a set of ingredient names
     */
    public Set<String> getIngredients() {
        return ingredients;
    }

    /**
     * Retrieves the username associated with the shopping list.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Clears the set of ingredients in the shopping list. If the ingredients set is null,
     * this method has no effect.
     */
    public void clearIngredients() {
        if (ingredients != null) {
            ingredients.clear();
        }
    }
}

