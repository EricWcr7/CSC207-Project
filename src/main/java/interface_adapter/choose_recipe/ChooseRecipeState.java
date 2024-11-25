package interface_adapter.choose_recipe;

import java.util.ArrayList;
import java.util.List;

public class ChooseRecipeState {
    private String searchKeyword;
    private List<String> recipeNames; // List to store recipe names
    private String errorMessage;// Field to store error messages
    private String username;
    private String[] favoriteRecipes;

    // Default constructor
    public ChooseRecipeState() {
        this.recipeNames = new ArrayList<>();
        this.errorMessage = ""; // Initialize with an empty string
    }

    // Copy constructor
    public ChooseRecipeState(ChooseRecipeState copy) {
        this.searchKeyword = copy.searchKeyword;
        this.recipeNames = new ArrayList<>(copy.recipeNames);
        this.errorMessage = copy.errorMessage;
    }

    // Getter and Setter for searchKeyword
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    // Getter and Setter for recipeNames
    public List<String> getRecipeNames() {
        return recipeNames;
    }

    public void setRecipeNames(List<String> recipeNames) {
        this.recipeNames = recipeNames;
    }

    // Helper method to add a single recipe name
    public void addRecipeName(String recipeName) {
        this.recipeNames.add(recipeName);
    }

    // Getter and Setter for errorMessage
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // Method to clear the state
    public void clearState() {
        this.searchKeyword = "";
        this.recipeNames.clear();
        this.errorMessage = ""; // Clear the error message
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


