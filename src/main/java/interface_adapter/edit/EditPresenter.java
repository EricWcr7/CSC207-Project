package interface_adapter.edit;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_recipe.CreateRecipeViewModel;
import use_case.edit.EditOutputBoundary;

public class EditPresenter implements EditOutputBoundary {
    private final EditViewModel editViewModel;
    private final CreateRecipeViewModel createRecipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public EditPresenter(ViewManagerModel viewManagerModel,
                         CreateRecipeViewModel createRecipeViewModel,
                         EditViewModel editViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.editViewModel = editViewModel;
        this.createRecipeViewModel = createRecipeViewModel;
    }


    @Override
    public void showCreateView() {
        viewManagerModel.setState(createRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
