package use_case.delete_a_recipe;


import use_case.like_a_recipe.LikeRecipeInputData;

/**
 * Input Boundary for actions related to choose recipe.
 */
public interface DeleteRecipeInputBoundary {

    /**
     * Executes the like recipe use case.
     * @param likeRecipeInputData the input data
     */
    void execute(LikeRecipeInputData likeRecipeInputData);
}


