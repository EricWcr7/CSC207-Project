package use_case.login;

/**
 * Represents the input data for the login use case.
 * This class encapsulates the user-provided credentials (username and password) required
 * to authenticate a user during the login process. It serves as the data transfer object
 * between the controller and the interactor for the login use case.
 */
public class LoginInputData {

    private final String username;
    private final String password;

    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username provided by the user.
     *
     * @return the username
     */
    String getUsername() {
        return username;
    }

    /**
     * Gets the password provided by the user.
     *
     * @return the password
     */
    String getPassword() {
        return password;
    }
}
