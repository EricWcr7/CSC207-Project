package interface_adapter.shopping_list;

import java.util.Set;

import interface_adapter.ViewModel;

/**
 * The ShoppingListViewModel class is responsible for representing the state of the shopping list
 * in a format that can be directly used by the view layer. It extends the `ViewModel` class,
 * providing access to the current `ShoppingListState` and facilitating interaction between the
 * interface adapter layer and the view.
 * This class encapsulates the shopping list's state, including the username, recipe names, and
 * ingredients, and provides methods for retrieving these details in a structured manner. It
 * ensures that the view is kept up to date with changes in the shopping list's state.
 */
public class ShoppingListViewModel extends ViewModel<ShoppingListState> {

    public ShoppingListViewModel() {
        super("The Overall Shopping List");
        setState(new ShoppingListState());
    }

    /**
     * Retrieves the set of unique ingredients required for the shopping list.
     *
     * @return a set of ingredient names included in the shopping list
     */
    public Set<String> getIngredients() {
        return getState().getIngredients();
    }

    /**
     * Retrieves the list of recipe names marked as favorites in the shopping list.
     *
     * @return an array of recipe names included in the shopping list
     */
    public String[] getFavouriteRecipes() {
        return getState().getRecipeNames();
    }

    /**
     * Retrieves the username associated with the shopping list.
     *
     * @return the username of the user requesting the shopping list
     */
    public String getUsername() {
        return getState().getUsername();
    }
}

