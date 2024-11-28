package use_case.logout;

/**
 * Represents the input data for the Logout Use Case.
 * This class encapsulates the data required to process a user's logout request,
 * specifically the username of the user who is logging out. It serves as a data
 * transfer object between the controller and the interactor.
 */
public class LogoutInputData {
    private String username;

    public LogoutInputData(String username) {
        this.username = username;
    }

    /**
     * Gets the username of the user who is logging out.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }
}
