package use_case.like_a_recipe;

import data_access.RecipeDataAccessObject;
import entity.Recipe;
import use_case.login.LoginOutputData;

/**
 * The Like Recipe Interactor.
 * 从controller那准备好的input data知道哪个菜的名字，菜的原来的点赞数？
 * 把DAO里找对应菜的like number更新？
 * 把outputdata 菜的原来的点赞数+1 准备好
 * successful view就让presenter拿着更新点赞数
 */

public class LikeRecipeInteractor implements LikeRecipeInputBoundary {
    private final LikeRecipeOutputBoundary likeRecipePresenter;
    private final LikeRecipeDataAccessInterface recipeDataAccessObject;

    public LikeRecipeInteractor(LikeRecipeDataAccessInterface likeRecipeDataAccessInterface,
                                LikeRecipeOutputBoundary likeRecipePresenter) {
        this.likeRecipePresenter = likeRecipePresenter;
        this.recipeDataAccessObject = likeRecipeDataAccessInterface;
    }

    @Override
    public void execute(LikeRecipeInputData likeRecipeInputData) {

        final String recipeName = likeRecipeInputData.getDishName();
        final Recipe theRecipe = recipeDataAccessObject.getOneRecipe(recipeName);
        theRecipe.incrementLikeNumber();
        // Update the recipe in the database
        // recipeDataAccessObject.updateRecipe(theRecipe);
        final int updatedLikeNumber = theRecipe.getLikeNumber();
        final LikeRecipeOutputData likeRecipeOutputData = new LikeRecipeOutputData(recipeName, updatedLikeNumber);
        likeRecipePresenter.prepareSuccessView(likeRecipeOutputData);
    }

}
