package use_case.BackToEditView;

public class BackToEditViewInteractor implements BackToEditViewInputBoundary {
    private BackToEditViewOutputBoundary backToEditViewPresenter;

    public BackToEditViewInteractor(BackToEditViewOutputBoundary backToEditViewPresenter) {
        this.backToEditViewPresenter = backToEditViewPresenter;

    }

    @Override
    public void backToEditRecipeView(){
        backToEditViewPresenter.backToEditRecipeView();
    }

}
