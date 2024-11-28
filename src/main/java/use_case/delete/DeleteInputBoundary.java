package use_case.delete;

/**
 * Input Boundary for actions which are related to Delete.
 */
public interface DeleteInputBoundary {
    /**
     * Executes the Delete use case.
     * @param inputData the input data
     */
    void execute(DeleteInputData inputData);
}

