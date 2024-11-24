package interface_adapter.recipe_search;

public class RecipeSearchState {
    private String searchKeyword = "";
    private String errorMessage = ""; // New field to store error messages
    private final int six = 6;
    private String username = "";
    private String[] favoriteRecipes = new String[six];

    // Sets the search keyword for a successful search
    public void setSearchKeyWord(String searchKeyword) {
        this.searchKeyword = searchKeyword;
        this.errorMessage = ""; // Clear any error messages on new search
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    // Sets an error message for a failed search
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // Clears the search keyword and error message
    public void clearState() {
        this.searchKeyword = "";
        this.errorMessage = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(String[] favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }
}




