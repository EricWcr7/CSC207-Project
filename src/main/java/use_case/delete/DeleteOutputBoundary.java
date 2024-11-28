package use_case.delete;

/**
 * The output boundary for the Delete Use Case.
 */
public interface DeleteOutputBoundary {
    /**
     * Prepares the success view for the Delete Use Case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Delete Use Case.
     */
    void prepareFailureView();
}

