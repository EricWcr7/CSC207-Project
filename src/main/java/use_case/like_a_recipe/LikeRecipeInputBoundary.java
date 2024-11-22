package use_case.like_a_recipe;


import use_case.choose_recipe.ChooseRecipeInputData;

/**
 * Input Boundary for actions related to choose recipe.
 */
public interface LikeRecipeInputBoundary {

    /**
     * Executes the like recipe use case.
     * @param likeRecipeInputData the input data
     */
    void execute(LikeRecipeInputData likeRecipeInputData);
}


