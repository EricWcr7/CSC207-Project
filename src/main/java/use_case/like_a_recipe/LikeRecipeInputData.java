package use_case.like_a_recipe;

public class LikeRecipeInputData {
    private final String dishName;
    private final int likeNumber;


    public LikeRecipeInputData(String dishName, int likeNumber) {
        this.dishName = dishName;
        this.likeNumber = likeNumber;
    }
}
