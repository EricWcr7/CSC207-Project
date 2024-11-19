package interface_adapter.favorite_recipe;

import interface_adapter.ViewModel;
import interface_adapter.recipe_search.RecipeSearchState;

public class FavoriteRecipeViewModel extends ViewModel<FavoriteRecipeState> {

    public FavoriteRecipeViewModel() {
        super("my favorite recipe");
        setState(new FavoriteRecipeState());
    }
}
