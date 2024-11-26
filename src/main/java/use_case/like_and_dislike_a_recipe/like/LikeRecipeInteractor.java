package use_case.like_and_dislike_a_recipe.like;

import entity.Recipe;
import use_case.like_and_dislike_a_recipe.*;

import java.util.List;

/**
 * The Like Recipe Interactor.
 * 从controller那准备好的input data知道哪个菜的名字
 * 把outputdata 菜的原来的点赞数+1 准备好
 * 把DAO里找对应菜的like number更新
 * successful view就让presenter拿着更新点赞数
 */

public class LikeRecipeInteractor implements LikeAndDislikeRecipeInputBoundary {
    private final LikeRecipeOutputBoundary likeRecipePresenter;
    private final LikeAndDislikeRecipeDataAccessInterface recipeDataAccessObject;
    private final UserLikeAndDislikeDataAccessInterface likeRecipeDataAccessObject;

    public LikeRecipeInteractor(LikeAndDislikeRecipeDataAccessInterface likeRecipeDataAccessInterface,
                                UserLikeAndDislikeDataAccessInterface likeRecipeDataAccessObject,
                                LikeRecipeOutputBoundary likeRecipePresenter) {
        this.likeRecipePresenter = likeRecipePresenter;
        this.recipeDataAccessObject = likeRecipeDataAccessInterface;
        this.likeRecipeDataAccessObject = likeRecipeDataAccessObject;
    }

    @Override
    public void execute(LikeAndDislikeRecipeInputData likeRecipeInputData) {

        final String recipeName = likeRecipeInputData.getDishName();
        final Recipe theRecipe = recipeDataAccessObject.getOneRecipe(recipeName);

        // Check if the user has already liked the recipe
        final boolean alreadyLiked = likeRecipeDataAccessObject.hasUserLikedRecipe(recipeName);

        if (alreadyLiked) {
            //likeRecipePresenter.prepareFailureView("You have already liked this recipe");
            System.out.println("You have already liked this recipe");
            return;
        }

        // Increment like count and persist
        theRecipe.incrementLikeNumber();

        // Write updated recipes to file and delete the old file
        final List<Recipe> updatedRecipes = recipeDataAccessObject.getCachedRecipes();
        recipeDataAccessObject.writeRecipesToFile(updatedRecipes);
        recipeDataAccessObject.deleteFileFromFileIo();
        recipeDataAccessObject.uploadFileToFileIo();

        // Add the like to user data
        likeRecipeDataAccessObject.addLikedRecipe(recipeName);

        final int updatedLikeNumber = theRecipe.getLikeNumber();
        final LikeRecipeOutputData likeRecipeOutputData = new LikeRecipeOutputData(recipeName, updatedLikeNumber);
        likeRecipePresenter.prepareSuccessView(likeRecipeOutputData);
    }

}
