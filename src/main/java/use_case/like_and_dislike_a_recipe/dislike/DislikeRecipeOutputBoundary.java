package use_case.like_and_dislike_a_recipe.dislike;

public interface DislikeRecipeOutputBoundary {

    /**
     * Prepares the success view for the dislike recipe Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(DislikeRecipeOutputData outputData);

    void prepareFailureView(String dislikedMessage);
}
