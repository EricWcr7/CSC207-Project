package use_case.signup;

/**
 * Represents the output data for the Signup Use Case.
 * This class encapsulates the results of a signup operation, including the username
 * of the new account and a flag indicating whether the signup process failed.
 * It serves as a data transfer object between the interactor and the presenter.
 */
public class SignupOutputData {

    private final String username;

    private final boolean useCaseFailed;

    public SignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the username of the new account.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the signup process failed.
     *
     * @return {@code true} if the signup process failed; {@code false} otherwise
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
