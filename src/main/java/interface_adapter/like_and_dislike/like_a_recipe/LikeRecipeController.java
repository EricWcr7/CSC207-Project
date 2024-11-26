package interface_adapter.like_and_dislike.like_a_recipe;

import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeInputBoundary;
import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeInputData;

/**
 * Like a Recipe Controller.
 */
public class LikeRecipeController {

    private final LikeAndDislikeRecipeInputBoundary likeRecipeInteractor;

    public LikeRecipeController(LikeAndDislikeRecipeInputBoundary likeRecipeInteractor) {
        this.likeRecipeInteractor = likeRecipeInteractor;
    }

    public void execute(String dishName) {

        final LikeAndDislikeRecipeInputData likeData = new LikeAndDislikeRecipeInputData(dishName);
        likeRecipeInteractor.execute(likeData);

        System.out.println("Like Recipe Controller, Like dish: " + dishName);
    }
}
