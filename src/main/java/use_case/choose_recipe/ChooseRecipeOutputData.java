package use_case.choose_recipe;

/**
 * Output Data for the Login Use Case.
 */
public class ChooseRecipeOutputData {

    private final String dishName;
    private final String dishIngredients;
    private final String dishInstructions;
    private int likeNumber;

    public ChooseRecipeOutputData(String dishName, String dishIngredients, String dishInstructions, int likeNumber) {
        this.dishName = dishName;
        this.dishIngredients = dishIngredients;
        this.dishInstructions = dishInstructions;
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

}
