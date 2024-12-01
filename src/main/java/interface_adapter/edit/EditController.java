package interface_adapter.edit;

import use_case.edit.EditInputBoundary;

/**
 * The EditController class serves as the controller in the Clean Architecture pattern.
 * It acts as the intermediary between the user interface (view) and the use case interactor,
 * handling user input and invoking appropriate methods on the interactor.
 *
 * This class is responsible for managing actions related to editing and creating new entities (e.g., recipes).
 * It delegates these actions to the EditInputBoundary interface, which ensures that the
 * logic remains decoupled and adheres to the dependency inversion principle.
 */
public class EditController {

    // The interactor responsible for handling the use case logic.
    // This is a dependency injected through the constructor to enable loose coupling and easy testing.
    private final EditInputBoundary editInteractor;

    /**
     * Constructor for the EditController class.
     * Initializes the controller with an EditInputBoundary implementation, which contains
     * the business logic for handling edit-related operations.
     *
     * @param editInteractor An implementation of the EditInputBoundary interface that handles edit operations.
     */
    public EditController(EditInputBoundary editInteractor) {
        // Assign the provided interactor to the controller's field for use in user action handling.
        this.editInteractor = editInteractor;
    }

    /**
     * Switches the current view to the creation view.
     * This method is triggered when the user opts to create a new item (e.g., recipe).
     * It delegates the action to the interactor's switchToCreateView method, ensuring that the
     * view transition logic is handled by the use case layer and not the controller.
     */
    public void switchToCreate() {
        // Invoke the use case interactor to handle the transition to the create view.
        editInteractor.switchToCreateView();
    }
}
