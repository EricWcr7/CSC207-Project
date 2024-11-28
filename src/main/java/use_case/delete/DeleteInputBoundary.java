package use_case.delete;

/**
 * DeleteInputBoundary is an interface that defines the contract for the input boundary
 * in the delete use case. It serves as the entry point for the use case interactor, ensuring
 * that the input data is correctly processed for deletion logic.
 */
public interface DeleteInputBoundary {

    /**
     * Executes the delete use case with the provided input data.
     * This method is responsible for orchestrating the deletion process by
     * passing the necessary information to the interactor layer.
     *
     * @param inputData the data required for the delete operation, encapsulated in a
     *                  {@code DeleteInputData} object. This typically includes the
     *                  unique identifier or other key data for the recipe to be deleted.
     */
    void execute(DeleteInputData inputData);
}

