package use_case.login;

import java.util.Arrays;

import entity.User;

/**
 * Handles the "login" use case by validating user credentials and managing user sessions.
 * The LoginInteractor interacts with the data access layer to verify if a user's credentials
 * are valid. Based on the validation result, it prepares the appropriate view (success or failure)
 * through the presenter. Additionally, it handles initialization of user storage and view switching
 * for signup operations.
 * This class follows the Clean Architecture design, serving as the interactor for the login use case.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();

        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final User user = userDataAccessObject.get(username);
            if (!password.equals(user.getPassword())) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {
                util.Session.initialize(user);
                userDataAccessObject.setCurrentUsername(user.getName());
                final LoginOutputData loginOutputData = new LoginOutputData(
                        user.getName(),
                        false,
                        user.getFavoriteRecipes());
                loginPresenter.prepareSuccessView(loginOutputData);
                System.out.println("Current logged in account: " + username);
                System.out.println("Session initialized for user: " + user.getName());
                System.out.println("Current favorite recipes: " + Arrays.toString(user.getFavoriteRecipes()));
            }
        }
    }

    @Override
    public void initializeUserStorage() {
        System.out.println("Initializing shared user storage...");
        final String fileKey = userDataAccessObject.findFileOnFileIo("all_users.json");
        System.out.println(fileKey);

        if (!fileKey.isEmpty()) {
            // Case 1: If the file exists, load it from File.io using the DAO
            System.out.println("File 'all_users.json' found on File.io with ID: " + fileKey);
            userDataAccessObject.loadUsersFromCloud();
            System.out.println("Users loaded from 'all_users.json' successfully.");
        }
    }

    @Override
    public void switchToSignupView() {
        loginPresenter.switchToSignupView();
    }
}
