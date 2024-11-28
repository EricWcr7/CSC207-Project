package use_case.like_and_dislike_a_recipe;

/**
 * Input Boundary for actions related to like a recipe.
 */
public interface LikeAndDislikeRecipeInputBoundary {

    /**
     * Executes the like recipe use case.
     * @param likeAndDislikeRecipeInputData the input data
     */
    void execute(LikeAndDislikeRecipeInputData likeAndDislikeRecipeInputData);

}
