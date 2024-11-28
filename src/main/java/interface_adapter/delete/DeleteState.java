package interface_adapter.delete;

/**
 * Enum representing the state of the delete operation.
 * It is used to indicate whether the delete operation was successful or failed.
 */
public enum DeleteState {

    /**
     * Represents a successful delete operation.
     * This state indicates that the recipe was deleted successfully from both
     * the local file and the cloud storage.
     */
    SUCCESS,

    /**
     * Represents a failed delete operation.
     * This state indicates that the recipe could not be deleted, either because
     * it was not found in the storage or due to other issues (e.g., file access errors).
     */
    FAILURE
}

