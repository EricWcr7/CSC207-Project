package use_case.create;

/**
 * The CreateOutputBoundary interface defines the contract for handling the output
 * of the "Create Recipe" use case. It provides methods to prepare views or feedback
 * based on the outcome of the recipe creation process, ensuring a clear separation
 * of input and output concerns in the use case.
 */
public interface CreateOutputBoundary {

    /**
     * Prepares the success view when the recipe creation process is completed successfully.
     * Implementing this method allows the user to receive positive feedback, such as
     * confirmation of the created recipe or navigation to the newly created recipe details.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view when the recipe creation process fails.
     * Implementing this method ensures the user is informed about the failure,
     * such as displaying an error message or providing suggestions for resolving issues
     * (e.g., duplicate names, missing information).
     */
    void prepareFailureView();
}
