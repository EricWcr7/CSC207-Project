package util;

import entity.User;

/**
 * A utility class to manage the current session.
 * It handles storing and accessing the currently logged-in user's information.
 */
public class Session {
    // 保存当前用户信息
    private static User currentUser;

    /**
     * Initializes the session with the given user.
     * This method is called during login.
     *
     * @param user the user who has logged in.
     */
    public static void initialize(User user) {
        currentUser = user;
    }

    /**
     * Returns the current logged-in user.
     *
     * @return the current user, or null if no user is logged in.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Clears the current session.
     * This method is called during logout.
     */
    public static void clear() {
        currentUser = null;
    }

    /**
     * Checks if a user is currently logged in.
     *
     * @return true if a user is logged in, false otherwise.
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
