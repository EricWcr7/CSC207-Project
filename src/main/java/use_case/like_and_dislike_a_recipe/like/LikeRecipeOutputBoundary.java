package use_case.like_and_dislike_a_recipe.like;

/**
 * The output boundary for the like a recipe Use Case.
 */
public interface LikeRecipeOutputBoundary {
    /**
     * Prepares the success view for the like recipe Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(LikeRecipeOutputData outputData);

}
