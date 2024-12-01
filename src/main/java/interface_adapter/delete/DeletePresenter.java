package interface_adapter.delete;

import use_case.delete.DeleteOutputBoundary;

import javax.swing.JOptionPane;

public class DeletePresenter implements DeleteOutputBoundary {
    private final DeleteViewModel deleteViewModel;


    /**
     * Constructor for the DeletePresenter class.
     * This class is responsible for handling the presentation logic related to the delete operation.
     * It initializes the presenter with a specific DeleteViewModel instance, which is used to manage
     * the state of the deletion process and communicate updates to the view.
     *
     * @param deleteViewModel The ViewModel instance that stores the current state of the delete operation.
     */
    public DeletePresenter(DeleteViewModel deleteViewModel) {
        // Assign the provided ViewModel instance to the presenter for managing delete states and UI updates.
        this.deleteViewModel = deleteViewModel;
    }

    /**
     * Prepares the success view for the recipe deletion process.
     * This method is called when a recipe is successfully deleted.
     * It updates the application's state to reflect the successful deletion and provides feedback to the user.
     */
    @Override
    public void prepareSuccessView() {
        // Update the ViewModel's delete state to SUCCESS, indicating the operation was successful.
        deleteViewModel.setDeleteState(DeleteState.SUCCESS);

        // Display a success message to the user in a dialog box.
        // The message "Recipe deleted successfully!" confirms the success of the deletion process.
        JOptionPane.showMessageDialog(null, "Recipe deleted successfully!");
    }


    /**
     * This method handles the failure scenario of the recipe deletion process.
     * It updates the application's state to reflect the failure by setting the ViewModel's
     * delete state to FAILURE and notifies the user with a pop-up message.
     * The user is informed that the recipe deletion attempt failed due to the recipe not being found.
     *
     * The purpose of this method is to ensure a consistent failure handling mechanism,
     * providing both internal state management and user feedback.
     */
    @Override
    public void prepareFailureView() {
        // Update the ViewModel state to indicate the deletion operation has failed.
        // This will inform other components of the application about the failure status.
        deleteViewModel.setDeleteState(DeleteState.FAILURE);

        // Display an error message to the user in a dialog box.
        // JOptionPane is a simple way to create pop-up message dialogs.
        // The message "Failed to delete recipe. Recipe not found!" is shown to the user,
        // providing clear feedback about the failure.
        JOptionPane.showMessageDialog(null, "Failed to delete recipe. Recipe not found!");
    }


}

