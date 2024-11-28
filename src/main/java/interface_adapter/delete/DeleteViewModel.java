package interface_adapter.delete;

/**
 * The DeleteViewModel class acts as a data container for the delete use case,
 * holding the current state of the delete operation. It allows the presenter
 * to update the state of the operation and the view to retrieve it for display or further actions.
 */
public class DeleteViewModel {

    /**
     * Stores the current state of the delete operation.
     * The state is represented using the DeleteState enum, which can be either SUCCESS or FAILURE.
     */
    private DeleteState deleteState;

    /**
     * Retrieves the current state of the delete operation.
     *
     * @return the current DeleteState, indicating whether the operation was successful or failed.
     */
    public DeleteState getDeleteState() {
        return deleteState;
    }

    /**
     * Sets the state of the delete operation.
     *
     * @param deleteState the new state of the delete operation, which should be either SUCCESS or FAILURE.
     *                    This value is typically set by the presenter to reflect the result of the operation.
     */
    public void setDeleteState(DeleteState deleteState) {
        this.deleteState = deleteState;
    }
}
