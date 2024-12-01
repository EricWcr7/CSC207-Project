package interface_adapter.delete;

/**
 * The DeleteState enum represents the possible states of a delete operation.
 * It is part of the interface adapter layer in the application, following the Clean Architecture principles.
 * This enum provides a clear and type-safe way to define the outcome of a deletion process.
 * Using an enum ensures that the delete state is limited to predefined constants, improving code readability.
 */
public enum DeleteState {

    /**
     * Represents a successful delete operation.
     * This state is used when the recipe or object has been successfully deleted.
     */
    SUCCESS,

    /**
     * Represents a failed delete operation.
     * This state is used when the deletion process encounters an issue,
     * such as the item not being found or a system error.
     */
    FAILURE
}

