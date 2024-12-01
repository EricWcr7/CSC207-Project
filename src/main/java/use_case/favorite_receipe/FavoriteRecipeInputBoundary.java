package use_case.favorite_receipe;

/**
 * Input Boundary for actions which are related to favoriteRecipe.
 */
public interface FavoriteRecipeInputBoundary {

    /**
     * Executes the FavoriteRecipe use case.
     * @param favoriteRecipeInputData the input data
     */
    void execute(FavoriteRecipeInputData favoriteRecipeInputData);

    /**
     * Executes the switchToShoppingListView method in FavoriteRecipe use case.
     */
    void switchToShoppingListView();
}
