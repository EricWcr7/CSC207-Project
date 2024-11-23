package use_case.favorite_receipe;

/**
 * Input Boundary for actions which are related to favoriteRecipe.
 */
public interface FavoriteRecipeInputBoundary {

    /**
     * Executes the login use case.
     * @param favoriteRecipeInputData the input data
     */
    void execute(FavoriteRecipeInputData favoriteRecipeInputData);
}
