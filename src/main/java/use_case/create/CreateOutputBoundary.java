package use_case.create;

/**
 * The output boundary for the Create Use Case.
 */
public interface CreateOutputBoundary {

    /**
     * Prepares the success view for the Create Use Case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Create Use Case.
     */
    void prepareFailureView();

}
