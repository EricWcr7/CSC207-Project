package interface_adapter.favorite_recipe;

import use_case.favorite_receipe.FavoriteRecipeInputBoundary;

/**
 * The controller for the FavoriteRecipe Use Case.
 */
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
