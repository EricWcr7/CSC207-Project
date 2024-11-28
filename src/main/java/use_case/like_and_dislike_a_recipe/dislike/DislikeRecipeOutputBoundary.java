package use_case.like_and_dislike_a_recipe.dislike;

/**
 * The output boundary for the Dislike a Recipe Use Case.
 */
public interface DislikeRecipeOutputBoundary {

    /**
     * Prepares the success view for the dislike recipe Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(DislikeRecipeOutputData outputData);

    /**
     * Prepares the failure view for the dislike recipe Use Case.
     * @param dislikedMessage the error message indicating the failure reason.
     */
    void prepareFailureView(String dislikedMessage);
}
