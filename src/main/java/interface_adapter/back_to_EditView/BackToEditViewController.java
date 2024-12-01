package interface_adapter.back_to_EditView;

import use_case.back_to_EditView.BackToEditViewInputBoundary;

/**
 * Controller class responsible for handling the navigation back to the edit view.
 * It communicates with the interactor to execute the required business logic.
 */
public class BackToEditViewController {

    private final BackToEditViewInputBoundary backToEditViewInteractor;

    /**
     * Constructs a BackToEditViewController with the given interactor.
     *
     * @param backToEditViewInteractor the interactor used to handle the back-to-edit-view operation.
     */
    public BackToEditViewController(BackToEditViewInputBoundary backToEditViewInteractor) {
        this.backToEditViewInteractor = backToEditViewInteractor;
    }

    /**
     * Executes the action of navigating back to the edit view.
     * Delegates the operation to the interactor.
     */
    public void backToEditView() {
        backToEditViewInteractor.backToEditRecipeView();
    }
}

