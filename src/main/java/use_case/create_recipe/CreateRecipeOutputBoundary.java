package use_case.create_recipe;

/**
 * The output boundary for the Create Use Case.
 */
public interface CreateRecipeOutputBoundary {

    /**
     * Prepares the success view for the Create Use Case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Create Use Case.
     */
    void prepareFailureView();

}
