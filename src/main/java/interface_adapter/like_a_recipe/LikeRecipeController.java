package interface_adapter.like_a_recipe;


import use_case.like_a_recipe.LikeRecipeInputData;
import use_case.like_a_recipe.LikeRecipeInteractor;

public class LikeRecipeController {

    private final LikeRecipeInteractor likeRecipeInteractor;

    public LikeRecipeController(LikeRecipeInteractor likeRecipeInteractor) {
        this.likeRecipeInteractor = likeRecipeInteractor;
    }

    public void execute(String dishName, int likeNumber) {

        final LikeRecipeInputData likeData = new LikeRecipeInputData(dishName, likeNumber);
        likeRecipeInteractor.execute(likeData);

        System.out.println("Like dish: " + dishName + ", before like number: " + likeNumber);
    }
}
