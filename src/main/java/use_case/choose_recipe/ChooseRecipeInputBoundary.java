package use_case.choose_recipe;

/**
 * Input Boundary for actions related to choose recipe.
 */
public interface ChooseRecipeInputBoundary {

    /**
     * Executes the recipe search use case.
     * @param chooseRecipeInputData the input data
     */
    void execute(ChooseRecipeInputData chooseRecipeInputData);
}

