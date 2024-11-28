package use_case.backToEditView;

/**
 * The BackToEditView Interactor.
 */
public class BackToEditViewInteractor implements BackToEditViewInputBoundary {
    private BackToEditViewOutputBoundary backToEditViewPresenter;

    public BackToEditViewInteractor(BackToEditViewOutputBoundary backToEditViewPresenter) {
        this.backToEditViewPresenter = backToEditViewPresenter;

    }

    @Override
    public void backToEditRecipeView() {
        backToEditViewPresenter.backToEditRecipeView();
    }

}
