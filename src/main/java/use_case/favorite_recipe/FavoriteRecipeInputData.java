package use_case.favorite_recipe;

/**
 * Represents the input data for the "favorite recipe" use case.
 * This class encapsulates the data required to process a user's request to mark recipes as favorites,
 * including the username of the current user and the names of the recipes to be favorited.
 * It serves as a data transfer object between the controller and the interactor.
 */
public class FavoriteRecipeInputData {
    private final String username;
    private String[] recipeNames;

    public FavoriteRecipeInputData(String username, String[] recipeNames) {
        this.username = username;
        this.recipeNames = recipeNames;
    }

    /**
     * Gets the username of the currently logged-in user.
     *
     * @return the username of the current user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the names of the recipes to be marked as favorites.
     *
     * @return an array of recipe names
     */
    public String[] getRecipeNames() {
        return recipeNames;
    }

    /**
     * Sets the names of the recipes to be marked as favorites.
     *
     * @param recipeNames an array of recipe names
     */
    public void setRecipeNames(String[] recipeNames) {
        this.recipeNames = recipeNames;
    }
}
