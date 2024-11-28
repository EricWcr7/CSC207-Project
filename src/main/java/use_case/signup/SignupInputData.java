package use_case.signup;

/**
 * Represents the input data for the Signup Use Case.
 * This class encapsulates the data required for a user to create an account, including
 * the desired username, password, and a repeated password for confirmation. It serves
 * as a data transfer object between the controller and the interactor.
 */
public class SignupInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;

    public SignupInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    /**
     * Gets the desired username for the new account.
     *
     * @return the username
     */
    String getUsername() {
        return username;
    }

    /**
     * Gets the desired password for the new account.
     *
     * @return the password
     */
    String getPassword() {
        return password;
    }

    /**
     * Gets the repeated password for confirmation.
     *
     * @return the repeated password
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }
}
