package interface_adapter.recipe_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.choose_recipe.ChooseRecipeViewModel;
import interface_adapter.favorite_recipe.FavoriteRecipeState;
import interface_adapter.favorite_recipe.FavoriteRecipeViewModel;
import interface_adapter.signup.SignupState;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.recipe_search.RecipeSearchOutputData;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeSearchPresenter implements RecipeSearchOutputBoundary {
    private final RecipeSearchViewModel recipeSearchViewModel;
    private final ChooseRecipeViewModel chooseRecipeViewModel;
    private final FavoriteRecipeViewModel favoriteRecipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public RecipeSearchPresenter(ViewManagerModel viewManagerModel,
                                 ChooseRecipeViewModel chooseRecipeViewModel,
                                 FavoriteRecipeViewModel favoriteRecipeViewModel,
                                 RecipeSearchViewModel recipeSearchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.favoriteRecipeViewModel = favoriteRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(RecipeSearchOutputData outputData) {
        final ChooseRecipeState chooseRecipeState = chooseRecipeViewModel.getState();
        final RecipeSearchState recipeSearchState = recipeSearchViewModel.getState();
        final FavoriteRecipeState favoriteRecipeState = favoriteRecipeViewModel.getState();

        // Clear any previous keyword or error message
        recipeSearchState.setSearchKeyWord("");
        recipeSearchState.setErrorMessage("");
        this.recipeSearchViewModel.firePropertyChanged();

        this.favoriteRecipeViewModel.setState(favoriteRecipeState);
        this.favoriteRecipeViewModel.firePropertyChanged();

        // Check if there are results
        List<String> recipeNames = outputData.getRecipes().stream()
                .map(recipe -> recipe.getName())
                .collect(Collectors.toList());

//        if (recipeNames.isEmpty()) {
//            recipeSearchState.setErrorMessage("No recipes found for the keyword.");
//            this.recipeSearchViewModel.firePropertyChanged();
//            viewManagerModel.setState(recipeSearchViewModel.getViewName());
//        } else {
        chooseRecipeState.setSearchKeyword(outputData.getSearchKeyword());
        chooseRecipeState.setRecipeNames(recipeNames);

            // Notify changes in ChooseRecipeState and switch view
        this.chooseRecipeViewModel.setState(chooseRecipeState);
        this.chooseRecipeViewModel.firePropertyChanged();
        this.viewManagerModel.setState(chooseRecipeViewModel.getViewName());
//        }
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
    public void switchToFavoriteRecipeView() {
        viewManagerModel.setState(favoriteRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}


