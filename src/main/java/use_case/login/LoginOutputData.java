package use_case.login;

/**
 * Represents the output data for the Login Use Case.
 * This class encapsulates the result of the login process, including the username of the logged-in user,
 * a flag indicating whether the use case failed, and the user's favorite recipes.
 * It serves as a data transfer object between the interactor and the presenter.
 */
public class LoginOutputData {

    private final String username;
    private final boolean useCaseFailed;
    private final String[] favoriteRecipes;

    public LoginOutputData(String username, boolean useCaseFailed, String[] favoriteRecipes) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.favoriteRecipes = favoriteRecipes;
    }

    /**
     * Gets the username of the logged-in user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the user's favorite recipes.
     *
     * @return an array of favorite recipes
     */
    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }
}
