package interface_adapter.ReturnToSearchMenu;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.choose_recipe.ChooseRecipeViewModel;
import interface_adapter.edit.EditState;
import interface_adapter.edit.EditViewModel;
import interface_adapter.display_recipe.DisplayRecipeViewModel;
import interface_adapter.favorite_recipe.FavoriteRecipeState;
import interface_adapter.favorite_recipe.FavoriteRecipeViewModel;
import interface_adapter.recipe_search.RecipeSearchState;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuOutputBoundary;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuOutputData;
import view.ChooseRecipeView;

public class ReturnToSearchMenuPresenter implements ReturnToSearchMenuOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private RecipeSearchViewModel recipeSearchViewModel;
    private ChooseRecipeViewModel chooseRecipeViewModel;
    private EditViewModel editViewModel;
    private DisplayRecipeViewModel displayRecipeViewModel;
    private FavoriteRecipeViewModel favoriteRecipeViewModel;

    public ReturnToSearchMenuPresenter(ViewManagerModel viewManagerModel,
                                       RecipeSearchViewModel recipeSearchViewModel,
                                       ChooseRecipeViewModel chooseRecipeViewModel,
                                       DisplayRecipeViewModel displayRecipeViewModel,
                                       FavoriteRecipeViewModel favoriteRecipeViewModel,
                                       EditViewModel editViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        this.displayRecipeViewModel = displayRecipeViewModel;
        this.favoriteRecipeViewModel = favoriteRecipeViewModel;
        this.editViewModel = editViewModel;
    }

    @Override
    public void prepareSuccessView(ReturnToSearchMenuOutputData recipeSearchOutputData) {
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

        final RecipeSearchState currentState1 = recipeSearchViewModel.getState();
        currentState1.setUsername(recipeSearchOutputData.getUsername());
        currentState1.setFavoriteRecipes(recipeSearchOutputData.getFavoriteRecipes());
        recipeSearchViewModel.setState(currentState1);
        // 返回recipeSearch View
        this.viewManagerModel.setState(recipeSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void fromFavoriteRecipeBackToSearchMenu(ReturnToSearchMenuOutputData recipeSearchOutputData) {
        final FavoriteRecipeState favoriteRecipeState = favoriteRecipeViewModel.getState();
        final RecipeSearchState recipeSearchState = recipeSearchViewModel.getState();

        recipeSearchState.setUsername(recipeSearchOutputData.getUsername());
        recipeSearchState.setFavoriteRecipes(recipeSearchOutputData.getFavoriteRecipes());

        recipeSearchViewModel.setState(recipeSearchState);

        this.favoriteRecipeViewModel.setState(favoriteRecipeState);
        this.favoriteRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(recipeSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void fromEditRecipeBackToSearchMenu() {
        final EditState editRecipeState = editViewModel.getState();

        this.editViewModel.setState(editRecipeState);
        this.editViewModel.firePropertyChanged();

        this.viewManagerModel.setState(recipeSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
