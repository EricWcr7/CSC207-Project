package interface_adapter.recipe_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.choose_recipe.ChooseRecipeViewModel;
import interface_adapter.edit.EditState;
import interface_adapter.edit.EditViewModel;
import interface_adapter.favorite_recipe.FavoriteRecipeState;
import interface_adapter.favorite_recipe.FavoriteRecipeViewModel;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.recipe_search.RecipeSearchOutputData;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeSearchPresenter implements RecipeSearchOutputBoundary {
    private final RecipeSearchViewModel recipeSearchViewModel;
    private final ChooseRecipeViewModel chooseRecipeViewModel;
    private final FavoriteRecipeViewModel favoriteRecipeViewModel;
    private final EditViewModel editViewModel;
    private final ViewManagerModel viewManagerModel;

    public RecipeSearchPresenter(ViewManagerModel viewManagerModel,
                                 ChooseRecipeViewModel chooseRecipeViewModel,
                                 FavoriteRecipeViewModel favoriteRecipeViewModel,
                                 EditViewModel editViewModel,
                                 RecipeSearchViewModel recipeSearchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.favoriteRecipeViewModel = favoriteRecipeViewModel;
        this.editViewModel = editViewModel;
    }

    @Override
    public void prepareSuccessView(RecipeSearchOutputData outputData) {
        final ChooseRecipeState chooseRecipeState = chooseRecipeViewModel.getState();
        final RecipeSearchState recipeSearchState = recipeSearchViewModel.getState();
        final FavoriteRecipeState favoriteRecipeState = favoriteRecipeViewModel.getState();
        final EditState editState = editViewModel.getState();

        // Clear any previous keyword or error message
        recipeSearchState.setSearchKeyWord("");
        recipeSearchState.setErrorMessage("");
        this.recipeSearchViewModel.firePropertyChanged();

        this.favoriteRecipeViewModel.setState(favoriteRecipeState);
        this.editViewModel.setState(editState);
        this.favoriteRecipeViewModel.firePropertyChanged();

        // Check if there are results
        final List<String> recipeNames = outputData.getRecipes().stream()
                .map(recipe -> recipe.getName())
                .collect(Collectors.toList());
        chooseRecipeState.setSearchKeyword(outputData.getSearchKeyword());
        chooseRecipeState.setRecipeNames(recipeNames);
        this.chooseRecipeViewModel.setState(chooseRecipeState);
        this.chooseRecipeViewModel.firePropertyChanged();
        final ChooseRecipeState currentState = chooseRecipeViewModel.getState();
        currentState.setUsername(outputData.getUsername());
        currentState.setFavoriteRecipes(outputData.getFavoriteRecipes());
        chooseRecipeViewModel.setState(currentState);
        this.viewManagerModel.setState(chooseRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        // Set the error message in RecipeSearchState
        final RecipeSearchState recipeSearchState = recipeSearchViewModel.getState();
        recipeSearchState.setErrorMessage(errorMessage);

        // Notify the view model of the state change
        recipeSearchViewModel.firePropertyChanged();

    }

    @Override
    public void switchToFavoriteRecipeView(RecipeSearchOutputData outputData) {
        final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
        currentState.setUsername(outputData.getUsername());
        currentState.setFavoriteRecipes(outputData.getFavoriteRecipes());
        favoriteRecipeViewModel.setState(currentState);
        favoriteRecipeViewModel.firePropertyChanged();
        viewManagerModel.setState(favoriteRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToEditView() {
        viewManagerModel.setState(editViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println("RecipeSearchPresenter work");
    }
}
