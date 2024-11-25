package use_case.login;

/**
 * Output Data for the Login Use Case.
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

    public String getUsername() {
        return username;
    }

    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }
}
