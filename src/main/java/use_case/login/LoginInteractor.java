package use_case.login;

import entity.Recipe;
import entity.User;
import use_case.signup.SignupInputBoundary;

import java.util.Arrays;
import java.util.List;

/**
 * The Login Interactor.
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
            final String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User user = userDataAccessObject.get(loginInputData.getUsername());

                userDataAccessObject.setCurrentUsername(user.getName());
                final LoginOutputData loginOutputData = new LoginOutputData(
                        user.getName(),
                        false,
                        user.getFavoriteRecipes());
                loginPresenter.prepareSuccessView(loginOutputData);
                System.out.println("Current logged in account: " + username);
                System.out.println("Current favoriteRecipe in account: " + Arrays.toString(user.getFavoriteRecipes()));
            }
        }
    }

    @Override
    public void initializeUserStorage() {
        System.out.println("Initializing shared user storage...");
        try {
            // Step 1: Check if "all_users.json" exists on File.io using the DAO
            String fileKey = userDataAccessObject.findFileOnFileIo("all_users.json");
            System.out.println(fileKey);

            if (!fileKey.isEmpty()) {
                // Case 1: If the file exists, load it from File.io using the DAO
                System.out.println("File 'all_users.json' found on File.io with ID: " + fileKey);
                userDataAccessObject.loadUsersFromCloud(); // Load users from the existing JSON file
                System.out.println("Users loaded from 'all_users.json' successfully.");
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize user storage: " + e.getMessage());
        }
    }


    public void switchToSignupView() {
        loginPresenter.switchToSignupView();
    }
}


