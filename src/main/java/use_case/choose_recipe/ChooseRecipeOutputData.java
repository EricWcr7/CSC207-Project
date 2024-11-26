package use_case.choose_recipe;

/**
 * Output Data for the Login Use Case.
 */
public class ChooseRecipeOutputData {

    private final String dishName;
    private final String dishIngredients;
    private final String dishInstructions;
    private int likeNumber;
    private final String username;
    private final String[] favoriteRecipes;

    public ChooseRecipeOutputData(String dishName, String dishIngredients, String dishInstructions,
                                  int likeNumber, String username, String[] favoriteRecipes) {
        this.dishName = dishName;
        this.dishIngredients = dishIngredients;
        this.dishInstructions = dishInstructions;
        this.username = username;
        this.favoriteRecipes = favoriteRecipes;
        this.likeNumber = likeNumber;
    }

    public String getDishName() {
        return dishName;
    }

    public String getDishIngredients() {
        return dishIngredients;
    }

    public String getDishInstructions() {
        return dishInstructions;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public String getUsername() {
        return username;
    }

    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }
}
