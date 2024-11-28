package use_case.like_and_dislike_a_recipe;

/**
 * The Input Data for the Like and Dislike a Recipe Use Case.
 */
public class LikeAndDislikeRecipeInputData {
    private final String dishName;

    public LikeAndDislikeRecipeInputData(String dishName) {
        this.dishName = dishName;
    }

    public String getDishName() {
        return dishName;
    }
}
