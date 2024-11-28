package use_case.create;

/**
 * Input Boundary for actions which are related to Create.
 */
public interface CreateInputBoundary {

    /**
     * Executes the Create use case.
     * @param createInputData the input data
     */
    void execute(CreateInputData createInputData);
}
