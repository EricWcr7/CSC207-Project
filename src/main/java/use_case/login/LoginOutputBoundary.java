package use_case.login;

/**
 * The output boundary for the Login Use Case.
 * This interface defines the methods required to prepare views based on the outcome of the login process.
 * It serves as the contract between the interactor and the presenter, ensuring that the appropriate views
 * are prepared for success or failure scenarios and providing navigation to the signup view when needed.
 */
public interface LoginOutputBoundary {
    /**
     * Prepares the success view for the Login Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(LoginOutputData outputData);

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches the application view to the signup interface.
     * This method allows navigation to the signup view, enabling users to create a new account
     * if they cannot log in with existing credentials.
     */
    void switchToSignupView();
}
