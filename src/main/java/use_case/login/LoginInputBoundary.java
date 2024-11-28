package use_case.login;

/**
 * Input Boundary for actions related to the login use case.
 * This interface defines the methods required to handle the user login process,
 * including initializing user storage and switching to the signup view.
 * It serves as the contract between the controller and the interactor.
 */
public interface LoginInputBoundary {

    /**
     * Executes the login use case.
     * @param loginInputData the input data
     */
    void execute(LoginInputData loginInputData);

    /**
     * Switches the application view to the signup interface.
     * This method is used to navigate the user to the signup view when they
     * choose to create a new account instead of logging in.
     */
    void switchToSignupView();

    /**
     * Initializes the storage for user data.
     * This method sets up the necessary storage mechanisms for user data, ensuring
     * that all required resources are available for the login and user management processes.
     */
    void initializeUserStorage();
}
