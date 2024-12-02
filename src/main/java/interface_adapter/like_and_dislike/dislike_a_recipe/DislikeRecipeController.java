package interface_adapter.like_and_dislike.dislike_a_recipe;

import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeInputBoundary;
import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeInputData;

/**
 * Dislike a Recipe Controller.
 */
public class DislikeRecipeController {

    private final LikeAndDislikeRecipeInputBoundary dislikeRecipeInteractor;

    public DislikeRecipeController(LikeAndDislikeRecipeInputBoundary likeRecipeInteractor) {
        this.dislikeRecipeInteractor = likeRecipeInteractor;
    }

    /**
     * Executes the dislike action for the specified dish name.
     * Creates an input data object encapsulating the dish name and passes it to the interactor.
     * @param dishName the name of the dish to be disliked.
     */
    public void execute(String dishName) {

        final LikeAndDislikeRecipeInputData dislikeData = new LikeAndDislikeRecipeInputData(dishName);
        dislikeRecipeInteractor.execute(dislikeData);

        System.out.println("Dislike Recipe Controller, Dislike dish: " + dishName);
    }

}
