package use_case.like_and_dislike_a_recipe.dislike;


/**
 * Represents the output data for the "dislike a recipe" use case.
 * This class encapsulates the result of a user's action to dislike a recipe,
 * including the name of the recipe and the updated number of dislikes.
 * It serves as the data transfer object between the interactor and the presenter.
 */
public class DislikeRecipeOutputData {
    private String recipeName;
    private int dislikeNumber;

    public DislikeRecipeOutputData(String recipeName, int dislikeNumber) {
        this.recipeName = recipeName;
        this.dislikeNumber = dislikeNumber;
    }

    /**
     * Gets the updated number of dislikes for the recipe.
     *
     * @return the current dislike count for the recipe
     */
    public int getDislikeNumber() {
        return dislikeNumber;
    }

    /**
     * Gets the name of the recipe that was disliked.
     *
     * @return the name of the disliked recipe
     */
    public String getRecipeName() {
        return recipeName;
    }
}
