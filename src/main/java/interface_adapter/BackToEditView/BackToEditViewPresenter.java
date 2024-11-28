package interface_adapter.BackToEditView;

import interface_adapter.ViewManagerModel;
import interface_adapter.create.CreateState;
import interface_adapter.create.CreateViewModel;
import interface_adapter.edit.EditViewModel;
import use_case.backToEditView.BackToEditViewOutputBoundary;

import java.util.HashMap;

public class BackToEditViewPresenter implements BackToEditViewOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private EditViewModel editViewModel;
    private CreateViewModel createViewModel;

    public BackToEditViewPresenter(ViewManagerModel viewManagerModel,
                                   EditViewModel editViewModel,
                                   CreateViewModel createViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.editViewModel = editViewModel;
        this.createViewModel = createViewModel;
    }

    @Override
    public void backToEditRecipeView() {
        final CreateState currentState = createViewModel.getState();
        currentState.setDishNameError("");
        currentState.setDishName("");
        currentState.setInstructions("");
        currentState.setIngredients(new HashMap<String, String>());
        this.createViewModel.setState(currentState);
        this.createViewModel.firePropertyChanged("Back to search");

        this.viewManagerModel.setState(editViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
