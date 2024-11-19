package interface_adapter.BackToEditView;

import interface_adapter.ViewManagerModel;
import interface_adapter.create.CreateState;
import interface_adapter.create.CreateViewModel;
import interface_adapter.edit.EditViewModel;
import use_case.BackToEditView.BackToEditViewOutputBoundary;

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
    public void backToEditRecipeView(){
        final CreateState createrecipestate = createViewModel.getState();

        this.createViewModel.setState(createrecipestate);
        this.createViewModel.firePropertyChanged();

        this.viewManagerModel.setState(editViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
