package use_case.delete;

/**
 * DeleteOutputBoundary is an interface that defines the contract for the output boundary
 * in the delete use case. It specifies the methods required to handle the success or
 * failure of the delete operation and prepares the appropriate response for the presenter layer.
 */
public interface DeleteOutputBoundary {

    /**
     * Prepares the success view when the delete operation completes successfully.
     * This method should be implemented to define how to notify the user or
     * system about the successful deletion of a recipe.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view when the delete operation fails.
     * This method should be implemented to define how to notify the user or
     * system about the failure of the deletion process, including potential reasons.
     */
    void prepareFailureView();
}
