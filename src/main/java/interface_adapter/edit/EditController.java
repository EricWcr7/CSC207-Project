package interface_adapter.edit;

import use_case.edit.EditInputBoundary;

/**
 * The EditController class serves as the intermediary between the user interface (UI)
 * and the business logic of the edit use case. It handles user actions related to editing
 * and delegates the processing to the interactor through the input boundary.
 */
public class EditController {

    /**
     * A reference to the EditInputBoundary interface, which defines the input
     * methods for the edit use case. The controller uses this interactor to
     * trigger business logic for editing or view transitions.
     */
    private final EditInputBoundary editInteractor;

    /**
     * Constructs an EditController with the specified EditInputBoundary.
     *
     * @param editInteractor the input boundary implementation that contains the business
     *                       logic for the edit use case.
     */
    public EditController(EditInputBoundary editInteractor) {
        this.editInteractor = editInteractor;
    }

    /**
     * Switches the current view to the create view.
     * This method delegates the request to the interactor, which handles
     * the logic for transitioning from the edit view to the create view.
     */
    public void switchToCreate() {
        // Delegate the request to the interactor to handle the view transition
        editInteractor.switchToCreateView();
    }
}
