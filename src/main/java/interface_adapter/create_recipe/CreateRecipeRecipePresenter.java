package interface_adapter.create_recipe;

import java.util.HashMap;

import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.create_recipe.CreateRecipeOutputBoundary;

/**
 * The CreatePresenter class serves as the presenter in the "Create Recipe" use case.
 * It is responsible for updating the view models and notifying the UI of changes
 * based on the outcome of the recipe creation process. It implements the
 *  CreateOutputBoundary interface to handle success and failure scenarios.
 */
public class CreateRecipeRecipePresenter implements CreateRecipeOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RecipeSearchViewModel recipeSearchViewModel;
    private final CreateRecipeViewModel createRecipeViewModel;

    public CreateRecipeRecipePresenter(ViewManagerModel viewManagerModel, RecipeSearchViewModel recipeSearchViewModel,
                                       CreateRecipeViewModel createRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.createRecipeViewModel = createRecipeViewModel;
    }

    @Override
    public void prepareSuccessView() {
        final CreateRecipeState currentState = createRecipeViewModel.getState();
        currentState.setDishNameError("");
        currentState.setDishName("");
        currentState.setInstructions("");
        currentState.setIngredients(new HashMap<String, String>());
        this.createRecipeViewModel.setState(currentState);
        this.createRecipeViewModel.firePropertyChanged("create recipe");
        viewManagerModel.setState(recipeSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView() {
        final CreateRecipeState currentState = createRecipeViewModel.getState();
        currentState.setDishNameError("Recipe Name already exists, please choose another one");
        createRecipeViewModel.firePropertyChanged("dishNameError");
    }

}
