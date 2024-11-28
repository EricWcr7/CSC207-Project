package use_case.logout;

/**
 * Represents the output data for the Logout Use Case.
 * This class encapsulates the result of a user's logout request, including the username of the user
 * and a flag indicating whether the logout process failed. It serves as a data transfer object
 * between the interactor and the presenter.
 */
public class LogoutOutputData {

    private String username;
    private boolean useCaseFailed;

    public LogoutOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the username of the user attempting to log out.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks whether the logout process failed.
     *
     * @return {@code true} if the logout process failed, {@code false} otherwise
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
