package use_case.back_to_EditView;

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
