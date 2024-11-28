package use_case.like_and_dislike_a_recipe.dislike;

import java.util.List;

import entity.Recipe;
import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeDataAccessInterface;
import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeInputBoundary;
import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeInputData;
import use_case.like_and_dislike_a_recipe.UserLikeAndDislikeDataAccessInterface;

/**
 * The Dislike Recipe Interactor.
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

        // Check if the user has already disliked the recipe
        final boolean alreadyDisliked = dislikeRecipeDataAccessObject.hasUserDislikedRecipe(recipeName);

        if (alreadyDisliked) {
            dislikeRecipePresenter.prepareFailureView("You have already disliked this recipe");
            System.out.println("You have already disliked this recipe");
        }
        else {
            // Increment dislike count and persist
            theRecipe.incrementDislikeNumber();

            // Write updated recipes to file and delete the old file
            final List<Recipe> updatedRecipes = recipeDataAccessObject.getCachedRecipes();
            recipeDataAccessObject.writeRecipesToFile(updatedRecipes);
            recipeDataAccessObject.deleteFileFromFileIo();
            recipeDataAccessObject.uploadFileToFileIo();

            // Add the dislike to user data
            dislikeRecipeDataAccessObject.addDislikedRecipe(recipeName);
            dislikeRecipeDataAccessObject.updateUserDislikedRecipe(recipeName);

            final int updatedDislikeNumber = theRecipe.getDislikeNumber();
            final DislikeRecipeOutputData dislikeRecipeOutputData = new DislikeRecipeOutputData(recipeName, updatedDislikeNumber);
            dislikeRecipePresenter.prepareSuccessView(dislikeRecipeOutputData);
        }

    }

}
