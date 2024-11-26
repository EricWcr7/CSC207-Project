package use_case.like_and_dislike_a_recipe.dislike;

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

public class DislikeRecipeInteractor implements LikeAndDislikeRecipeInputBoundary {
    private final DislikeRecipeOutputBoundary dislikeRecipePresenter;
    private final LikeAndDislikeRecipeDataAccessInterface recipeDataAccessObject;
    private final UserLikeAndDislikeDataAccessInterface dislikeRecipeDataAccessObject;

    public DislikeRecipeInteractor(LikeAndDislikeRecipeDataAccessInterface dislikeRecipeDataAccessInterface,
                                   UserLikeAndDislikeDataAccessInterface dislikeRecipeDataAccessObject,
                                   DislikeRecipeOutputBoundary dislikeRecipePresenter) {
        this.dislikeRecipePresenter = dislikeRecipePresenter;
        this.recipeDataAccessObject = dislikeRecipeDataAccessInterface;
        this.dislikeRecipeDataAccessObject = dislikeRecipeDataAccessObject;
    }

    @Override
    public void execute(LikeAndDislikeRecipeInputData dislikeRecipeInputData) {

        final String recipeName = dislikeRecipeInputData.getDishName();
        final Recipe theRecipe = recipeDataAccessObject.getOneRecipe(recipeName);

        // Check if the user has already liked the recipe
        final boolean alreadyDisliked = dislikeRecipeDataAccessObject.hasUserDislikedRecipe(recipeName);

        if (alreadyDisliked) {
            //likeRecipePresenter.prepareFailureView("You have already liked this recipe");
            System.out.println("You have already disliked this recipe");
            return;
        }

        // Increment like count and persist
        theRecipe.incrementDislikeNumber();

        // Write updated recipes to file and delete the old file
        final List<Recipe> updatedRecipes = recipeDataAccessObject.getCachedRecipes();
        recipeDataAccessObject.writeRecipesToFile(updatedRecipes);
        recipeDataAccessObject.deleteFileFromFileIo();
        recipeDataAccessObject.uploadFileToFileIo();

        // Add the like to user data
        dislikeRecipeDataAccessObject.addDislikedRecipe(recipeName);

        final int updatedDislikeNumber = theRecipe.getDislikeNumber();
        final DislikeRecipeOutputData dislikeRecipeOutputData = new DislikeRecipeOutputData(recipeName, updatedDislikeNumber);
        dislikeRecipePresenter.prepareSuccessView(dislikeRecipeOutputData);
    }

}
