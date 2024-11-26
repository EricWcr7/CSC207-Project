package interface_adapter.like_and_dislike.dislike_a_recipe;

import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeInputBoundary;
import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeInputData;

/**
 * Dislike a Recipe Controller.
 */
public class DislikeRecipeController {

    private final LikeAndDislikeRecipeInputBoundary likeRecipeInteractor;

    public DislikeRecipeController(LikeAndDislikeRecipeInputBoundary likeRecipeInteractor) {
        this.likeRecipeInteractor = likeRecipeInteractor;
    }

    public void execute(String dishName) {

        final LikeAndDislikeRecipeInputData likeData = new LikeAndDislikeRecipeInputData(dishName);
        likeRecipeInteractor.execute(likeData);

        System.out.println("Dislike Recipe Controller, Dislike dish: " + dishName);
    }
}
