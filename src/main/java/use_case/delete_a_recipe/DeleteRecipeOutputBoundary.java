package use_case.delete_a_recipe;

import use_case.like_a_recipe.LikeRecipeOutputData;

/**
 * The output boundary for the Login Use Case.
 */
public interface DeleteRecipeOutputBoundary {
    /**
     * Prepares the success view for the like recipe Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(LikeRecipeOutputData outputData);
}
