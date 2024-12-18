package interface_adapter.choose_recipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_recipe.DisplayRecipeState;
import interface_adapter.display_recipe.DisplayRecipeViewModel;
import use_case.choose_recipe.ChooseRecipeOutputBoundary;
import use_case.choose_recipe.ChooseRecipeOutputData;

import java.util.List;

public class ChooseRecipePresenter implements ChooseRecipeOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ChooseRecipeViewModel chooseRecipeViewModel;
    private final DisplayRecipeViewModel displayRecipeViewModel;

    public ChooseRecipePresenter(ViewManagerModel viewManagerModel,
                                 ChooseRecipeViewModel chooseRecipeViewModel,
                                  DisplayRecipeViewModel displayRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        this.displayRecipeViewModel = displayRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(ChooseRecipeOutputData outputData) {
        final DisplayRecipeState displayState = displayRecipeViewModel.getState();

        // Debug: Before setting the state
        System.out.println("Before updating state in ViewModel:");
        System.out.println("Dish Name: " + outputData.getDishName());
//        System.out.println("Ingredients: " + outputData.getDishIngredients());
//        System.out.println("Instructions: " + outputData.getDishInstructions());

        // Update the display state
        final String dishName = outputData.getDishName();
        displayState.setDishName(outputData.getDishName());
        displayState.setIngredients(outputData.getDishIngredients());
        displayState.setInstructions(outputData.getDishInstructions());
        displayState.setLikeNumber(dishName, outputData.getLikeNumber());
        displayState.setDislikeNumber(dishName, outputData.getDislikeNumber());
        this.displayRecipeViewModel.setState(displayState);
        this.displayRecipeViewModel.firePropertyChanged();
        System.out.println("Setting new state in ViewModel with data: ");
        System.out.println("Dish Name: " + displayState.getDishName());
        final DisplayRecipeState currentState = displayRecipeViewModel.getState();
        currentState.setUsername(outputData.getUsername());
        currentState.setFavoriteRecipes(outputData.getFavoriteRecipes());
        displayRecipeViewModel.setState(currentState);
        this.viewManagerModel.setState(displayRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the recipe list view by updating the ViewModel with the given recipe names.
     * @param recipeNames the list of recipe names to be displayed in the view
     */
    public void presentRecipeList(List<String> recipeNames) {
        chooseRecipeViewModel.getState().setRecipeNames(recipeNames);
        chooseRecipeViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a selected recipe by setting the selected recipe name.
     * @param selectedRecipe the name of the selected recipe
     */
    public void presentSelectedRecipe(String selectedRecipe) {
        chooseRecipeViewModel.getState().setSearchKeyword(selectedRecipe);
        chooseRecipeViewModel.firePropertyChanged();
    }

    /**
     * Prepares a failure view if there is an error message to display.
     * @param errorMessage the error message to display in the view
     */
    public void presentError(String errorMessage) {
        chooseRecipeViewModel.getState().setErrorMessage(errorMessage);
        chooseRecipeViewModel.firePropertyChanged();
    }
}

