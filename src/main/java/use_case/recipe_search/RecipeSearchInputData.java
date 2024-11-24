package use_case.recipe_search;

public class RecipeSearchInputData {
    private final String searchKeyword;
    private final String username;
    private String[] favoriteRecipes;

    public RecipeSearchInputData(String searchKeyword, String username, String[] favoriteRecipes) {
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

    public void setFavoriteRecipes(String[] favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }
}
