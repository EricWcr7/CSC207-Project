package interface_adapter.delete;

/**
 * ViewModel class for the Delete Use Case.
 * Acts as a data container that holds the state of the delete operation.
 * This class is used by the presenter to update the UI based on the operation's result.
 */
public class DeleteViewModel {

    // Field representing the current state of the delete operation.
    // It can be either SUCCESS or FAILURE, as defined in the DeleteState enum.
    private DeleteState deleteState;

    /**
     * Gets the current state of the delete operation.
     * This method is typically called by the view to determine the outcome
     * of the operation and update the UI accordingly.
     *
     * @return the current delete state, either SUCCESS or FAILURE.
     */
    public DeleteState getDeleteState() {
        return deleteState;
    }

    /**
     * Sets the state of the delete operation.
     * This method is typically called by the presenter to update the state
     * based on the result of the delete operation.
     *
     * @param deleteState the new state of the delete operation, which should be
     *                    one of the values defined in the DeleteState enum.
     */
    public void setDeleteState(DeleteState deleteState) {
        this.deleteState = deleteState;
    }
}

