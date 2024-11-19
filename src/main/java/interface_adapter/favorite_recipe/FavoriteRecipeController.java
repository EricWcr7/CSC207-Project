package interface_adapter.favorite_recipe;

import use_case.recipe_search.RecipeSearchInputBoundary;
import use_case.recipe_search.RecipeSearchInputData;
import use_case.favorite_receipe.FavoriteRecipeInputBoundary;

public class FavoriteRecipeController {
    private final FavoriteRecipeInputBoundary favoriteRecipeInteractor;

    public FavoriteRecipeController(FavoriteRecipeInputBoundary favoriteRecipeInteractor) {
        this.favoriteRecipeInteractor = favoriteRecipeInteractor;
    }


    /**
     * Executes the Recipe Search Use Case.
     *
     */
    public void execute() {
    }
}
