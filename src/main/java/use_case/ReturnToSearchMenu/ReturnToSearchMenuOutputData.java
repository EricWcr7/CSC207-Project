package use_case.ReturnToSearchMenu;

public class ReturnToSearchMenuOutputData {
    private final String searchKeyword;
    private final String username;
    private final String[] favoriteRecipes;

    public ReturnToSearchMenuOutputData(String searchKeyword, String username, String[] favoriteRecipes) {
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
