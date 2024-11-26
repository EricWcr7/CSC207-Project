package interface_adapter.like_and_dislike.dislike_a_recipe;

import interface_adapter.display_recipe.DisplayRecipeState;
import interface_adapter.display_recipe.DisplayRecipeViewModel;
import use_case.like_and_dislike_a_recipe.dislike.DislikeRecipeOutputBoundary;
import use_case.like_and_dislike_a_recipe.dislike.DislikeRecipeOutputData;

public class DislikeRecipePresenter implements DislikeRecipeOutputBoundary {
    private final DisplayRecipeViewModel displayRecipeViewModel;

    public DislikeRecipePresenter(DisplayRecipeViewModel displayRecipeViewModel) {
        this.displayRecipeViewModel = displayRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(DislikeRecipeOutputData outputData) {
        final DisplayRecipeState state = displayRecipeViewModel.getState();
        state.setDislikeNumber(outputData.getRecipeName(), outputData.getDislikeNumber());
        this.displayRecipeViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView(String dislikedMessage) {
        final DisplayRecipeState state = displayRecipeViewModel.getState();
        state.setDislikedMessage(dislikedMessage);
        this.displayRecipeViewModel.firePropertyChanged();
    }
}
