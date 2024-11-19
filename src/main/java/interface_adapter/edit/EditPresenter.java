package interface_adapter.edit;

import interface_adapter.ViewManagerModel;
import interface_adapter.create.CreateViewModel;
import use_case.edit.EditOutputBoundary;

public class EditPresenter implements EditOutputBoundary {
    private final EditViewModel editViewModel;
    private final CreateViewModel createViewModel;
    private final ViewManagerModel viewManagerModel;

    public EditPresenter(ViewManagerModel viewManagerModel,
                         CreateViewModel createViewModel,
                         EditViewModel editViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.editViewModel = editViewModel;
        this.createViewModel = createViewModel;
    }


    @Override
    public void showCreateView() {
        viewManagerModel.setState(createViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
