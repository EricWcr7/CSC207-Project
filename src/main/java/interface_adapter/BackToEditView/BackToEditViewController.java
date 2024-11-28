package interface_adapter.BackToEditView;

import use_case.backToEditView.BackToEditViewInputBoundary;

public class BackToEditViewController {

    private BackToEditViewInputBoundary backToEditViewInteractor;

    public BackToEditViewController(BackToEditViewInputBoundary backToEditViewInteractor) {
        this.backToEditViewInteractor = backToEditViewInteractor;
    }

    public void backToEditView() {
        backToEditViewInteractor.backToEditRecipeView();
    }
}
