package use_case.choose_recipe;

/**
 * Represents the input data for the "choose recipe" use case.
 * This class encapsulates the data required to process a "choose recipe" request, including the name of the dish
 * the user is searching for, the username of the currently logged-in user, and the user's favorite recipes.
 * It serves as the data transfer object between the controller and the interactor.
 */
public class ChooseRecipeInputData {
    private final String dishName;
    private final String username;
    private final String[] favoriteRecipes;

    public ChooseRecipeInputData(String dishName, String username, String[] favoriteRecipes) {
        this.dishName = dishName;
        this.username = username;
        this.favoriteRecipes = favoriteRecipes;
    }

    /**
     * Gets the name of the dish to search for.
     *
     * @return the name of the dish
     */
    public String getDishName() {
        return dishName;
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
     * Gets the favorite recipes of the current user.
     *
     * @return an array of favorite recipes
     */
    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }
}
