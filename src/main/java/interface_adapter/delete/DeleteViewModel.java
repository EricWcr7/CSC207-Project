package interface_adapter.delete;

/**
 * The DeleteViewModel class is part of the interface adapter layer in the application,
 * following the Clean Architecture principles. It acts as a bridge between the use case
 * layer and the view, holding the current state of the delete operation.
 *
 * This class encapsulates the state information for the delete process, ensuring that
 * the view can access and update this state in a clean and controlled manner.
 */
public class DeleteViewModel {

    // Field to store the current state of the delete operation.
    // This field uses the DeleteState enum to represent various states, such as SUCCESS or FAILURE.
    private DeleteState deleteState;

    /**
     * Retrieves the current state of the delete operation.
     *
     * @return the current delete state, which indicates whether the operation succeeded, failed, or is in progress.
     */
    public DeleteState getDeleteState() {
        return deleteState;
    }

    /**
     * Updates the current state of the delete operation.
     * This method is used by the presenter or other components to set the state based on
     * the result of the delete operation.
     *
     * @param deleteState The new state of the delete operation, represented by the DeleteState enum.
     */
    public void setDeleteState(DeleteState deleteState) {
        // Assign the provided state to the deleteState field.
        // This ensures that the view or other components can retrieve the updated state.
        this.deleteState = deleteState;
    }
}


