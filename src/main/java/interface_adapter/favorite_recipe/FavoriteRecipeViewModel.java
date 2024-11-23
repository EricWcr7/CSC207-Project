package interface_adapter.favorite_recipe;

import interface_adapter.ViewModel;

/**
 * The View Model for the FavoriteRecipe View.
 */
public class FavoriteRecipeViewModel extends ViewModel<FavoriteRecipeState> {

    public FavoriteRecipeViewModel() {
        super("my favorite recipe");
        setState(new FavoriteRecipeState());
    }
}
