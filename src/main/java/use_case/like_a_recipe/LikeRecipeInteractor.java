package use_case.like_a_recipe;

import entity.Recipe;

import java.util.List;

/**
 * The Like Recipe Interactor.
 * 从controller那准备好的input data知道哪个菜的名字
 * 把outputdata 菜的原来的点赞数+1 准备好
 * 把DAO里找对应菜的like number更新
 * successful view就让presenter拿着更新点赞数
 */

public class LikeRecipeInteractor implements LikeRecipeInputBoundary {
    private final LikeRecipeOutputBoundary likeRecipePresenter;
    private final LikeRecipeDataAccessInterface recipeDataAccessObject;
    private final UserLikesDataAccessInterface userLikesDataAccessObject;
    private final LikeRecipeUserNameDataAccessInterface likeRecipeUserNameDataAccessObject;

    public LikeRecipeInteractor(LikeRecipeDataAccessInterface likeRecipeDataAccessInterface,
                                UserLikesDataAccessInterface userLikesDataAccessInterface,
                                LikeRecipeUserNameDataAccessInterface likeRecipeUserNameDataAccessObject,
                                LikeRecipeOutputBoundary likeRecipePresenter) {
        this.likeRecipePresenter = likeRecipePresenter;
        this.recipeDataAccessObject = likeRecipeDataAccessInterface;
        this.userLikesDataAccessObject = userLikesDataAccessInterface;
        this.likeRecipeUserNameDataAccessObject = likeRecipeUserNameDataAccessObject;
    }

    @Override
    public void execute(LikeRecipeInputData likeRecipeInputData) {

        final String recipeName = likeRecipeInputData.getDishName();
        final Recipe theRecipe = recipeDataAccessObject.getOneRecipe(recipeName);

        // Check if the user has already liked the recipe
        final String userName = likeRecipeUserNameDataAccessObject.getCurrentUsername();
        final boolean alreadyLiked = userLikesDataAccessObject.hasUserLikedRecipe(userName, recipeName);

        if (alreadyLiked) {
            //likeRecipePresenter.prepareFailureView("You have already liked this recipe");
            System.out.println("You have already liked this recipe");
            return;
        }

        // Increment like count and persist
        theRecipe.incrementLikeNumber();
        // final String recipeId = theRecipe.getId();

        final List<Recipe> updatedRecipes = recipeDataAccessObject.getCachedRecipes();
        recipeDataAccessObject.writeRecipesToFile(updatedRecipes);
        recipeDataAccessObject.deleteFileFromFileIo();
        recipeDataAccessObject.uploadFileToFileIo();
        // recipeDataAccessObject.updateRecipeField(recipeId, "likeNumber");

        // Add the like to user data
        userLikesDataAccessObject.addUserLike(userName, recipeName);

        final int updatedLikeNumber = theRecipe.getLikeNumber();
        final LikeRecipeOutputData likeRecipeOutputData = new LikeRecipeOutputData(recipeName, updatedLikeNumber);
        likeRecipePresenter.prepareSuccessView(likeRecipeOutputData);
    }

}
