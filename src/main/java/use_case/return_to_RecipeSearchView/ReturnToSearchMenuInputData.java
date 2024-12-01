package use_case.return_to_RecipeSearchView;

/**
 * The ReturnToSearchMenu Data for the Login Use Case.
 */
public class ReturnToSearchMenuInputData {
    private final String searchKeyword;
    private final String username;
    private final String[] favoriteRecipes;

    public ReturnToSearchMenuInputData(String searchKeyword, String username, String[] favoriteRecipes) {
        this.searchKeyword = searchKeyword;
        this.username = username;
        this.favoriteRecipes = favoriteRecipes;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public String getUsername() {
        return username;
    }

    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }
}
