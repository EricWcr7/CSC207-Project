package use_case.edit;

/**
 * The EditInteractor class implements the EditInputBoundary interface.
 * It serves as the interactor in the Clean Architecture pattern, handling
 * the business logic for transitioning from the Edit View to the Create View.
 */
public class EditInteractor implements EditInputBoundary {

    // The output boundary interface responsible for presenting the results of this use case.
    private final EditOutputBoundary editPresenter;

    /**
     * Constructs an EditInteractor instance with a specified output boundary.
     *
     * @param editPresenter the output boundary that will handle the presentation logic
     *                      for transitioning to the Create View.
     */
    public EditInteractor(EditOutputBoundary editPresenter) {
        this.editPresenter = editPresenter;
    }

    /**
     * Handles the logic for switching from the Edit View to the Create View.
     * This method delegates the presentation logic to the provided EditOutputBoundary instance.
     */
    @Override
    public void switchToCreateView() {
        // Delegates the task of showing the Create View to the presenter.
        editPresenter.showCreateView();
    }
}
