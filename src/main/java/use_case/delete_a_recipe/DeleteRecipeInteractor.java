package use_case.delete_a_recipe;

import use_case.like_a_recipe.LikeRecipeInputBoundary;
import use_case.like_a_recipe.LikeRecipeInputData;

/**
 * The Like Recipe Interactor.
 * 从controller那准备好的input data知道哪个菜的名字，菜的原来的点赞数？
 * 把DAO里找对应菜的like number更新？
 * 把outputdata 菜的原来的点赞数+1 准备好
 * successful view就让presenter拿着更新点赞数
 */

public class DeleteRecipeInteractor implements LikeRecipeInputBoundary {
    private final LikeRecipeInputBoundary likeRecipePresenter;
    // private final RecipeDataAccessObject recipeDataAccessObject;

    public DeleteRecipeInteractor(LikeRecipeInputBoundary likeRecipePresenter) {
        this.likeRecipePresenter = likeRecipePresenter;
    }

    @Override
    public void execute(LikeRecipeInputData likeRecipeInputData) { }

}
