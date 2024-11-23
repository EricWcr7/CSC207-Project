package interface_adapter.like_a_recipe;


import use_case.like_a_recipe.LikeRecipeInputBoundary;
import use_case.like_a_recipe.LikeRecipeInputData;

public class LikeRecipeController {

    private final LikeRecipeInputBoundary likeRecipeInteractor;

    public LikeRecipeController(LikeRecipeInputBoundary likeRecipeInteractor) {
        this.likeRecipeInteractor = likeRecipeInteractor;
    }

    public void execute(String dishName) {

        final LikeRecipeInputData likeData = new LikeRecipeInputData(dishName);
        likeRecipeInteractor.execute(likeData);

        System.out.println("Like Recipe Controller, Like dish: " + dishName);
    }
}
