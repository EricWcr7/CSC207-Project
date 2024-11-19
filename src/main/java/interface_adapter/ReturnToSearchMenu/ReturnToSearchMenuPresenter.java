package interface_adapter.ReturnToSearchMenu;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.choose_recipe.ChooseRecipeViewModel;
import interface_adapter.display_recipe.DisplayRecipeViewModel;
import interface_adapter.favorite_recipe.FavoriteRecipeState;
import interface_adapter.favorite_recipe.FavoriteRecipeViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuOutputBoundary;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuOutputData;
import view.ChooseRecipeView;

public class ReturnToSearchMenuPresenter implements ReturnToSearchMenuOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private RecipeSearchViewModel recipeSearchViewModel;
    private ChooseRecipeViewModel chooseRecipeViewModel;
    private DisplayRecipeViewModel displayRecipeViewModel;
    private FavoriteRecipeViewModel favoriteRecipeViewModel;

    public ReturnToSearchMenuPresenter(ViewManagerModel viewManagerModel,
                                       RecipeSearchViewModel recipeSearchViewModel,
                                       ChooseRecipeViewModel chooseRecipeViewModel,
                                       DisplayRecipeViewModel displayRecipeViewModel,
                                       FavoriteRecipeViewModel favoriteRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        this.displayRecipeViewModel = displayRecipeViewModel;
        this.favoriteRecipeViewModel = favoriteRecipeViewModel;
    }

    @Override
    public void prepareSuccessView() {
        // Check the current view to determine the origin
        final String currentState = this.viewManagerModel.getState();

        if (currentState.equals(chooseRecipeViewModel.getViewName())) {
            // Reset state for ChooseRecipeViewModel
            final ChooseRecipeState chooseRecipeState = chooseRecipeViewModel.getState();
            chooseRecipeState.setSearchKeyword("");
            this.chooseRecipeViewModel.setState(chooseRecipeState);
            this.chooseRecipeViewModel.firePropertyChanged();
        } else if (currentState.equals(displayRecipeViewModel.getViewName())) {
            // Optionally reset DisplayRecipeViewModel state if needed
            // e.g., displayRecipeViewModel.clearState();
        }

        // 返回recipeSearch View
        this.viewManagerModel.setState(recipeSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
    @Override
    public void fromFavoriteRecipeBackToSearchMenu() {
        final FavoriteRecipeState favoriteRecipeState = favoriteRecipeViewModel.getState();

        this.favoriteRecipeViewModel.setState(favoriteRecipeState);
        this.favoriteRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(recipeSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
}
