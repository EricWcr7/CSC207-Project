package use_case.like_a_recipe;

import java.io.IOException;

/**
 * Input Boundary for actions related to like a recipe.
 */
public interface LikeRecipeInputBoundary {

    /**
     * Executes the like recipe use case.
     * @param likeRecipeInputData the input data
     * @throws IOException if an I/O error occurs during the update process.
     */
    void execute(LikeRecipeInputData likeRecipeInputData);

}


