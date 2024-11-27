package use_case.like_and_dislike_a_recipe;

public class LikeAndDislikeRecipeInputData {
    private final String dishName;

    public LikeAndDislikeRecipeInputData(String dishName) {
        this.dishName = dishName;
    }

    public String getDishName() {
        return dishName;
    }
}
