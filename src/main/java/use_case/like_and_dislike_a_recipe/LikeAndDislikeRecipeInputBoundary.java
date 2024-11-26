package use_case.like_and_dislike_a_recipe;

import java.io.IOException;

/**
 * Input Boundary for actions related to like a recipe.
 */
public interface LikeAndDislikeRecipeInputBoundary {

    /**
     * Executes the like recipe use case.
     * @param likeAndDislikeRecipeInputData the input data
     * @throws IOException if an I/O error occurs during the update process.
     */
    void execute(LikeAndDislikeRecipeInputData likeAndDislikeRecipeInputData);

}


