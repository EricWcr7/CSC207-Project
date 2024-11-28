package use_case.like_and_dislike_a_recipe.like;

/**
 * The output boundary for the Like a Recipe Use Case.
 */
public interface LikeRecipeOutputBoundary {
    /**
     * Prepares the success view for the like recipe Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(LikeRecipeOutputData outputData);

    /**
     * Prepares the failure view for the like recipe Use Case.
     * @param likedMessage the error message indicating the failure reason.
     */
    void prepareFailureView(String likedMessage);
}
