package interface_adapter.create;

import java.util.HashMap;

import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.create.CreateOutputBoundary;

/**
 * The CreatePresenter class serves as the presenter in the "Create Recipe" use case.
 * It is responsible for updating the view models and notifying the UI of changes
 * based on the outcome of the recipe creation process. It implements the
 *  CreateOutputBoundary interface to handle success and failure scenarios.
 */
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
