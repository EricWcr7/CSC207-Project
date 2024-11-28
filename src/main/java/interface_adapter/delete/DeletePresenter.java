package interface_adapter.delete;

import javax.swing.JOptionPane;

import use_case.delete.DeleteOutputBoundary;

/**
 * Presenter class for the Delete Use Case.
 * Acts as the output boundary for the Delete Use Case, responsible for updating the view model
 * with the outcome of the delete operation and displaying messages to the user.
 */
public class DeletePresenter implements DeleteOutputBoundary {

    // The view model that holds the state of the delete operation for the UI to display.
    private final DeleteViewModel deleteViewModel;

    /**
     * Constructor for DeletePresenter.
     * Initializes the presenter with a reference to the delete view model.
     *
     * @param deleteViewModel the view model that will store the result state of the delete operation.
     */
    public DeletePresenter(DeleteViewModel deleteViewModel) {
        this.deleteViewModel = deleteViewModel;
    }

    /**
     * Prepares the success view when the delete operation completes successfully.
     * Updates the view model to reflect the success state and displays a success message
     * to the user using a JOptionPane dialog.
     */
    @Override
    public void prepareSuccessView() {
        // Update the view model to indicate a successful delete operation.
        deleteViewModel.setDeleteState(DeleteState.SUCCESS);

        // Show a success message to the user in a popup dialog.
        JOptionPane.showMessageDialog(null, "Recipe deleted successfully!");
    }

    /**
     * Prepares the failure view when the delete operation fails.
     * Updates the view model to reflect the failure state and displays an error message
     * to the user using a JOptionPane dialog.
     */
    @Override
    public void prepareFailureView() {
        // Update the view model to indicate a failed delete operation.
        deleteViewModel.setDeleteState(DeleteState.FAILURE);

        // Show an error message to the user in a popup dialog.
        JOptionPane.showMessageDialog(null, "Failed to delete recipe. Recipe not found!");
    }
}

