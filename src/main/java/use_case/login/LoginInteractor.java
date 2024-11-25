package use_case.login;

import entity.User;

import java.util.Arrays;

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

    public void switchToSignupView() {
        loginPresenter.switchToSignupView();
    }
}


