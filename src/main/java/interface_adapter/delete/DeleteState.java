package interface_adapter.delete;

/**
 * The DeleteState enum represents the possible states of a delete operation
 * in the delete use case. It is used to indicate whether the operation was
 * successful or failed, and it allows clear communication of the operation's
 * outcome throughout the application.
 */
public enum DeleteState {

    /**
     * Indicates that the delete operation was completed successfully.
     * This state is used to inform the user or update the system that
     * the target recipe was successfully deleted.
     */
    SUCCESS,

    /**
     * Indicates that the delete operation failed.
     * This state is used to inform the user or update the system that
     * the delete operation could not be completed, such as when the
     * target recipe is not found.
     */
    FAILURE
}
