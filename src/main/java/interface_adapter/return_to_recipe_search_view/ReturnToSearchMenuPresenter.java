package interface_adapter.return_to_recipe_search_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.choose_recipe.ChooseRecipeViewModel;
import interface_adapter.display_recipe.DisplayRecipeState;
import interface_adapter.edit.EditState;
import interface_adapter.edit.EditViewModel;
import interface_adapter.display_recipe.DisplayRecipeViewModel;
import interface_adapter.favorite_recipe.FavoriteRecipeState;
import interface_adapter.favorite_recipe.FavoriteRecipeViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import interface_adapter.shopping_list.ShoppingListState;
import interface_adapter.shopping_list.ShoppingListViewModel;
import use_case.return_to_recipe_search_view.ReturnToSearchMenuOutputBoundary;

public class ReturnToSearchMenuPresenter implements ReturnToSearchMenuOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private RecipeSearchViewModel recipeSearchViewModel;
    private ChooseRecipeViewModel chooseRecipeViewModel;
    private EditViewModel editViewModel;
    private DisplayRecipeViewModel displayRecipeViewModel;
    private FavoriteRecipeViewModel favoriteRecipeViewModel;
    private ShoppingListViewModel shoppingListViewModel;

    public ReturnToSearchMenuPresenter(ViewManagerModel viewManagerModel,
                                       RecipeSearchViewModel recipeSearchViewModel,
                                       ChooseRecipeViewModel chooseRecipeViewModel,
                                       DisplayRecipeViewModel displayRecipeViewModel,
                                       FavoriteRecipeViewModel favoriteRecipeViewModel,
                                       EditViewModel editViewModel,
                                       ShoppingListViewModel shoppingListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        this.displayRecipeViewModel = displayRecipeViewModel;
        this.favoriteRecipeViewModel = favoriteRecipeViewModel;
        this.editViewModel = editViewModel;
        this.shoppingListViewModel = shoppingListViewModel;
    }

    @Override
    public void fromDisplayBackToSearchMenu() {
        final DisplayRecipeState displayState = displayRecipeViewModel.getState();

        this.displayRecipeViewModel.setState(displayState);
        this.displayRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(recipeSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void fromChooseBackToSearchMenu() {
        // Reset state for ChooseRecipeViewModel
        final ChooseRecipeState chooseRecipeState = chooseRecipeViewModel.getState();
        chooseRecipeState.setSearchKeyword("");
        this.chooseRecipeViewModel.setState(chooseRecipeState);
        this.chooseRecipeViewModel.firePropertyChanged();

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

    @Override
    public void fromShoppingListBackToSearchMenu() {
        final ShoppingListState shoppingListState = shoppingListViewModel.getState();
        shoppingListState.clearIngredients();
        this.shoppingListViewModel.setState(shoppingListState);
        this.shoppingListViewModel.firePropertyChanged();

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
