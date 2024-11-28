package use_case.login;

import entity.User;

/**
 * DAO for the Login Use Case.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);

    /**
     * Returns the username of the curren user of the application.
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUsername();

    /**
     * Sets the username indicating who is the current user of the application.
     * @param username the new current username; null to indicate that no one is currently logged into the application.
     */
    void setCurrentUsername(String username);

    /**
     * Searches for a file on File.io by its name and retrieves its unique file key.
     * This method interacts with the File.io API to search for a file matching the specified
     * name. If a file is found, its unique key is returned, allowing subsequent operations
     * like downloading or deleting the file.
     * @param fileName the name of the file to search for on File.io
     * @return the unique file key of the file if found; an empty string otherwise
     */
    String findFileOnFileIo(String fileName);

    /**
     * Downloads user data from cloud storage (e.g., File.io) and updates local storage.
     * This method retrieves the user data file using its unique file key from File.io, parses
     * the content to update the local storage with the user information, and optionally re-uploads
     * the updated data file to the cloud for consistency.
     * If the file key is not set or if an error occurs, appropriate logging and error handling are performed.
     */
    void loadUsersFromCloud();
}
