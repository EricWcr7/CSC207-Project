package use_case.favorite_receipe;

/**
 * The output boundary for the FavoriteRecipe Use Case.
 */
public interface FavoriteRecipeOutputBoundary {

    /**
     * Prepares the success view for the FavoriteRecipe Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(FavoriteRecipeOutputData outputData);

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailureView(String errorMessage);

    void switchToShoppingListView();
}
