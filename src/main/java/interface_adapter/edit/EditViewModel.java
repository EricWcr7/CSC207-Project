package interface_adapter.edit;

import interface_adapter.ViewModel;

/**
 * The EditViewModel class extends the generic ViewModel class and is specifically
 * designed to manage the state and data for the edit view in the application.
 * It initializes with an "Edit recipe" view name and uses an EditState instance
 * to track the state of the editing process.
 */
public class EditViewModel extends ViewModel<EditState> {

    /**
     * Constructs an EditViewModel instance with a default view name ("Edit recipe")
     * and initializes the state with a new EditState instance.
     * This ensures that the view model starts with a clean and consistent state
     * for the editing process.
     */
    public EditViewModel() {
        // Call the parent class constructor to set the view name
        super("Edit recipe");

        // Initialize the state with a new EditState instance
        setState(new EditState());
    }
}
