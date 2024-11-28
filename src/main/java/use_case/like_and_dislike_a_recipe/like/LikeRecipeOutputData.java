package use_case.like_and_dislike_a_recipe.like;

/**
 * Output Data for the Like a Recipe Use Case.
 */
public class LikeRecipeOutputData {
    private String recipeName;
    private int likeNumber;

    public LikeRecipeOutputData(String recipeName, int LikeNumber) {
        this.recipeName = recipeName;
        this.likeNumber = LikeNumber;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public String getRecipeName() {
        return recipeName;
    }
}
