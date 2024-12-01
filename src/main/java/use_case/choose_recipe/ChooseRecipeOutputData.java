package use_case.choose_recipe;


/**
 * Represents the output data for the "choose recipe" use case.
 * This class encapsulates the details of a chosen recipe, including the dish name, ingredients, instructions,
 * number of likes and dislikes, and user-specific information such as the username and favorite recipes.
 * It serves as the data transfer object between the interactor and the presenter, ensuring that all necessary
 * information for the view is available in a structured manner.
 */
public class ChooseRecipeOutputData {

    private final String dishName;
    private final String dishIngredients;
    private final String dishInstructions;
    private int likeNumber;
    private int dislikeNumber;

    public ChooseRecipeOutputData(String dishName, String dishIngredients, String dishInstructions,
                                  int likeNumber, int dislikeNumber) {
        this.dishName = dishName;
        this.dishIngredients = dishIngredients;
        this.dishInstructions = dishInstructions;
        this.likeNumber = likeNumber;
        this.dislikeNumber = dislikeNumber;
    }

    /**
     * Gets the name of the dish.
     *
     * @return the name of the dish
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * Gets the ingredients of the dish.
     *
     * @return the ingredients of the dish as a string
     */
    public String getDishIngredients() {
        return dishIngredients;
    }

    /**
     * Gets the instructions for preparing the dish.
     *
     * @return the instructions for the dish
     */
    public String getDishInstructions() {
        return dishInstructions;
    }

    /**
     * Gets the number of likes the dish has received.
     *
     * @return the number of likes
     */
    public int getLikeNumber() {
        return likeNumber;
    }

    /**
     * Gets the number of dislikes the dish has received.
     *
     * @return the number of dislikes
     */
    public int getDislikeNumber() {
        return dislikeNumber;
    }
}
