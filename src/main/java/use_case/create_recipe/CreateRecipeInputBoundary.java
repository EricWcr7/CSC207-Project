package use_case.create_recipe;

/**
 * Input Boundary for actions which are related to Create.
 */
public interface CreateRecipeInputBoundary {

    /**
     * Executes the Create use case.
     * @param createRecipeInputData the input data
     */
    void execute(CreateRecipeInputData createRecipeInputData);
}
