package interface_adapter.create;

import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.create.CreateOutputBoundary;
import use_case.recipe_search.RecipeSearchOutputData;

import java.util.HashMap;

public class CreatePresenter implements CreateOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RecipeSearchViewModel recipeSearchViewModel;
    private final CreateViewModel createViewModel;

    public CreatePresenter(ViewManagerModel viewManagerModel, RecipeSearchViewModel recipeSearchViewModel,
                           CreateViewModel createViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.createViewModel = createViewModel;
    }

    @Override
    public void prepareSuccessView() {
        final CreateState currentState = createViewModel.getState();
        currentState.setDishNameError("");
        currentState.setDishName("");
        currentState.setInstructions("");
        currentState.setIngredients(new HashMap<String, String>());
        this.createViewModel.setState(currentState);
        this.createViewModel.firePropertyChanged("create recipe");
        viewManagerModel.setState(recipeSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView() {
        final CreateState currentState = createViewModel.getState();
        currentState.setDishNameError("Recipe Name already exists, please choose another one");
        createViewModel.firePropertyChanged("dishNameError");
    }

}
