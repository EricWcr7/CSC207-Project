package use_case.delete;

/**
 * The DeleteOutputBoundary interface defines the contract for handling the output
 * of the delete use case. It provides methods to prepare views based on whether
 * the deletion was successful or failed.
 */
public interface DeleteOutputBoundary {

    /**
     * Prepares the success view when the deletion operation is completed successfully.
     * Implementing this method allows the user to receive feedback or navigate to
     * an appropriate success page or state.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view when the deletion operation fails.
     * Implementing this method ensures that the user receives appropriate feedback
     * about the failure, such as an error message or prompt to retry.
     */
    void prepareFailureView();
}

