package use_case.like_and_dislike_a_recipe.like;

import java.util.List;

import entity.Recipe;
import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeDataAccessInterface;
import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeInputBoundary;
import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeInputData;
import use_case.like_and_dislike_a_recipe.UserLikeAndDislikeDataAccessInterface;

/**
 * The Like Recipe Interactor.
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
            // Presenter prepare failure view
            likeRecipePresenter.prepareFailureView("You have already liked this recipe");
            System.out.println("You have already liked this recipe");
        }
        else {
            // Increment like count
            theRecipe.incrementLikeNumber();

            // Write updated recipes to file API
            final List<Recipe> updatedRecipes = recipeDataAccessObject.getCachedRecipes();
            recipeDataAccessObject.updateChangedRecipes(updatedRecipes);

            // Add the liked recipe to user data, and update the user data to file API
            likeRecipeDataAccessObject.addLikedRecipe(recipeName);
            likeRecipeDataAccessObject.updateUserLikedRecipe(recipeName);

            // Presenter prepare successful view
            final int updatedLikeNumber = theRecipe.getLikeNumber();
            final LikeRecipeOutputData likeRecipeOutputData = new LikeRecipeOutputData(recipeName, updatedLikeNumber);
            likeRecipePresenter.prepareSuccessView(likeRecipeOutputData);
        }

    }

}
