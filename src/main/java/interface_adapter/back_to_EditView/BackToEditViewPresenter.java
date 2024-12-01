package interface_adapter.back_to_EditView;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_recipe.CreateRecipeState;
import interface_adapter.create_recipe.CreateRecipeViewModel;
import interface_adapter.edit.EditViewModel;
import use_case.back_to_EditView.BackToEditViewOutputBoundary;

import java.util.HashMap;

public class BackToEditViewPresenter implements BackToEditViewOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private EditViewModel editViewModel;
    private CreateRecipeViewModel createRecipeViewModel;

    public BackToEditViewPresenter(ViewManagerModel viewManagerModel,
                                   EditViewModel editViewModel,
                                   CreateRecipeViewModel createRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.editViewModel = editViewModel;
        this.createRecipeViewModel = createRecipeViewModel;
    }

    @Override
    public void backToEditRecipeView() {
        final CreateRecipeState currentState = createRecipeViewModel.getState();
        currentState.setDishNameError("");
        currentState.setDishName("");
        currentState.setInstructions("");
        currentState.setIngredients(new HashMap<String, String>());
        this.createRecipeViewModel.setState(currentState);
        this.createRecipeViewModel.firePropertyChanged("Back to search");

        this.viewManagerModel.setState(editViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
