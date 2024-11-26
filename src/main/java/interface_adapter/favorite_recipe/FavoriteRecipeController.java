package interface_adapter.favorite_recipe;

import use_case.favorite_receipe.FavoriteRecipeInputBoundary;
import use_case.favorite_receipe.FavoriteRecipeInputData;
import use_case.like_a_recipe.LikeRecipeInputData;

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
     * @param username the username
     * @param favoriteRecipes the favoriteRecipe
     */
    public void execute(String username, String[] favoriteRecipes) {
        final FavoriteRecipeInputData favoriteRecipeInputData = new FavoriteRecipeInputData(username, favoriteRecipes);
        favoriteRecipeInteractor.execute(favoriteRecipeInputData);
    }
}
