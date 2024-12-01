package interface_adapter.edit;

import interface_adapter.ViewModel;

/**
 * The EditViewModel class extends the generic ViewModel class, specifically using the EditState type.
 * It represents the ViewModel for the editing functionality in the application.
 * This class is part of the interface adapter layer, following the Clean Architecture principles.
 *
 * The ViewModel acts as an intermediary between the application's UI (view) and the use case logic,
 * managing the state and providing a structured way to handle updates and transitions for the edit view.
 */
public class EditViewModel extends ViewModel<EditState> {

    /**
     * Constructor for the EditViewModel class.
     * Initializes the ViewModel with a default title ("Edit recipe") and sets the initial state.
     * The initial state is represented by a new instance of the EditState class, ensuring the ViewModel
     * starts with a clean state.
     */
    public EditViewModel() {
        // Call the constructor of the superclass ViewModel with the title "Edit recipe".
        super("Edit recipe");

        // Set the initial state of the ViewModel to a new instance of EditState.
        // This ensures that the ViewModel starts with a default, consistent state for editing operations.
        setState(new EditState());
    }
}
