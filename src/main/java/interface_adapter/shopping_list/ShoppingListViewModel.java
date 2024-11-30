package interface_adapter.shopping_list;

import interface_adapter.ViewModel;

import java.util.Set;

public class ShoppingListViewModel extends ViewModel<ShoppingListState> {

    public ShoppingListViewModel() {
        super("The Overall Shopping List");
        setState(new ShoppingListState());
    }

    public Set<String> getIngredients() {
        return getState().getIngredients();
    }

    public String[] getFavouriteRecipes() {
        return getState().getRecipeNames();
    }

    public String getUsername() {
        return getState().getUsername();
    }
}

