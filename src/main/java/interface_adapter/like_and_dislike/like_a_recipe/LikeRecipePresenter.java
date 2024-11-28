package interface_adapter.like_and_dislike.like_a_recipe;

import interface_adapter.display_recipe.DisplayRecipeState;
import interface_adapter.display_recipe.DisplayRecipeViewModel;
import use_case.like_and_dislike_a_recipe.like.LikeRecipeOutputBoundary;
import use_case.like_and_dislike_a_recipe.like.LikeRecipeOutputData;

/**
 * The Presenter for the Like a Recipe Use Case.
 */
public class LikeRecipePresenter implements LikeRecipeOutputBoundary {
    private final DisplayRecipeViewModel displayRecipeViewModel;

    public LikeRecipePresenter(DisplayRecipeViewModel displayRecipeViewModel) {
        this.displayRecipeViewModel = displayRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(LikeRecipeOutputData outputData) {
        final DisplayRecipeState state = displayRecipeViewModel.getState();
        state.setLikeNumber(outputData.getRecipeName(), outputData.getLikeNumber());
        this.displayRecipeViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView(String likedMessage) {
        final DisplayRecipeState state = displayRecipeViewModel.getState();
        state.setLikedMessage(likedMessage);
        this.displayRecipeViewModel.firePropertyChanged();
    }
}
