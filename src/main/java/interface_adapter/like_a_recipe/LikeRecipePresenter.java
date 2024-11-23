package interface_adapter.like_a_recipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_recipe.DisplayRecipeState;
import interface_adapter.display_recipe.DisplayRecipeViewModel;
import use_case.like_a_recipe.LikeRecipeOutputBoundary;
import use_case.like_a_recipe.LikeRecipeOutputData;

// 更新like number
public class LikeRecipePresenter implements LikeRecipeOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final DisplayRecipeViewModel displayRecipeViewModel;

    public LikeRecipePresenter(ViewManagerModel viewManagerModel,
                               DisplayRecipeViewModel displayRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayRecipeViewModel = displayRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(LikeRecipeOutputData outputData) {
        final DisplayRecipeState state = displayRecipeViewModel.getState();
        state.setLikeNumber(outputData.getRecipeName(), outputData.getLikeNumber());
        this.displayRecipeViewModel.setState(state);
        this.displayRecipeViewModel.firePropertyChanged();

    }
}
