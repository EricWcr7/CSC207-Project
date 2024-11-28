package use_case.like_and_dislike_a_recipe;

/**
 * Represents the input data for the "like and dislike a recipe" use case.
 * This class encapsulates the data required to perform an action of liking or disliking a recipe,
 * which includes the name of the recipe to be processed.
 * It serves as the data transfer object from the controller to the interactor.
 */
public class LikeAndDislikeRecipeInputData {
    private final String dishName;

    public LikeAndDislikeRecipeInputData(String dishName) {
        this.dishName = dishName;
    }

    /**
     * Gets the name of the recipe to be liked or disliked.
     *
     * @return the name of the recipe
     */
    public String getDishName() {
        return dishName;
    }
}
