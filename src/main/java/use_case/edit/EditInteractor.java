package use_case.edit;

public class EditInteractor implements EditInputBoundary {
    private final EditOutputBoundary editPresenter;

    public EditInteractor(EditOutputBoundary editPresenter) {
        this.editPresenter = editPresenter;
    }

    @Override
    public void switchToCreateView() {
        editPresenter.showCreateView();
    }
}