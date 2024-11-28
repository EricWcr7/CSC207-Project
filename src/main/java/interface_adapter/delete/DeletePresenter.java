package interface_adapter.delete;

import use_case.delete.DeleteOutputBoundary;

import javax.swing.JOptionPane;

/**
 * The DeletePresenter class implements the DeleteOutputBoundary interface and serves as
 * the presenter for the delete use case. It is responsible for preparing views and
 * communicating the results of the delete operation to the user interface (UI).
 */
public class DeletePresenter implements DeleteOutputBoundary {

    /**
     * A reference to the DeleteViewModel, which stores the state of the delete operation.
     * The presenter updates this view model to reflect the result of the delete operation.
     */
    private final DeleteViewModel deleteViewModel;

    /**
     * Constructs a DeletePresenter with the specified DeleteViewModel.
     *
     * @param deleteViewModel the view model used to track the state of the delete operation.
     */
    public DeletePresenter(DeleteViewModel deleteViewModel) {
        this.deleteViewModel = deleteViewModel;
    }

    /**
     * Prepares the success view when the delete operation is successful.
     * Updates the DeleteViewModel to indicate success and displays a success
     * message to the user using a dialog box.
     */
    @Override
    public void prepareSuccessView() {
        // Update the view model to reflect the successful delete state
        deleteViewModel.setDeleteState(DeleteState.SUCCESS);

        // Show a success message to the user
        JOptionPane.showMessageDialog(null, "Recipe deleted successfully!");
    }

    /**
     * Prepares the failure view when the delete operation fails.
     * Updates the DeleteViewModel to indicate failure and displays an error
     * message to the user using a dialog box.
     */
    @Override
    public void prepareFailureView() {
        // Update the view model to reflect the failure state
        deleteViewModel.setDeleteState(DeleteState.FAILURE);

        // Show an error message to the user
        JOptionPane.showMessageDialog(null, "Failed to delete recipe. Recipe not found!");
    }
}
