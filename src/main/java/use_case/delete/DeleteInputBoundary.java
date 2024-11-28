package use_case.delete;

/**
 * The DeleteInputBoundary interface defines the contract for the input boundary
 * of the delete use case. It acts as the entry point for handling delete requests,
 * ensuring that the input data is processed correctly to trigger the delete operation.
 */
public interface DeleteInputBoundary {

    /**
     * Executes the delete operation based on the provided input data.
     *
     * @param inputData an instance of DeleteInputData containing all necessary
     *                  information to perform the delete operation. This typically
     *                  includes identifiers for the item to be deleted and any
     *                  additional data required for validation or processing.
     */
    void execute(DeleteInputData inputData);
}
