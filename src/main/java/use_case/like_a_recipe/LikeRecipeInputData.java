package use_case.like_a_recipe;

public class LikeRecipeInputData {
    private final String dishName;

    public LikeRecipeInputData(String dishName) {
        this.dishName = dishName;
    }

    public String getDishName() {
        return dishName;
    }
}
