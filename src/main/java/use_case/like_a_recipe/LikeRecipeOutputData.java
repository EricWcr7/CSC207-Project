package use_case.like_a_recipe;

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
