package use_case.like_and_dislike_a_recipe.dislike;

/**
 * Output Data for the Dislike a Recipe Use Case.
 */
public class DislikeRecipeOutputData {
    private String recipeName;
    private int dislikeNumber;

    public DislikeRecipeOutputData(String recipeName, int dislikeNumber) {
        this.recipeName = recipeName;
        this.dislikeNumber = dislikeNumber;
    }

    public int getDislikeNumber() {
        return dislikeNumber;
    }

    public String getRecipeName() {
        return recipeName;
    }
}
