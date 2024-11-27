package interface_adapter.delete;

import use_case.delete.DeleteOutputBoundary;

import javax.swing.JOptionPane;

public class DeletePresenter implements DeleteOutputBoundary {
    private final DeleteViewModel deleteViewModel;


    public DeletePresenter(DeleteViewModel deleteViewModel) {
        this.deleteViewModel = deleteViewModel;
    }

    @Override
    public void prepareSuccessView() {

        deleteViewModel.setDeleteState(DeleteState.SUCCESS);


        JOptionPane.showMessageDialog(null, "Recipe deleted successfully!");
    }

    @Override
    public void prepareFailureView() {

        deleteViewModel.setDeleteState(DeleteState.FAILURE);


        JOptionPane.showMessageDialog(null, "Failed to delete recipe. Recipe not found!");
    }
}

