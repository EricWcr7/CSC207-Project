package use_case.favorite_recipe;

/**
 * The output boundary for the FavoriteRecipe Use Case.
 */
public interface FavoriteRecipeOutputBoundary {

    /**
     * Prepares the success view for the FavoriteRecipe Use Case.
     * @param outputData the output data
     */
    void updateFavoriteRecipe(FavoriteRecipeOutputData outputData);

    /**
     * Prepares the failure view for the FavoriteRecipe Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailureView(String errorMessage);

    /**
     * Prepares the switchToShoppingListView for the FavoriteRecipe Use Case.
     */
    void switchToShoppingListView();
}
