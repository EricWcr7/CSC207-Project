package interface_adapter.delete;

import use_case.delete.DeleteOutputBoundary;

import javax.swing.JOptionPane;

public class DeletePresenter implements DeleteOutputBoundary {
    private final DeleteViewModel deleteViewModel;

    // 构造方法，注入 ViewModel
    public DeletePresenter(DeleteViewModel deleteViewModel) {
        this.deleteViewModel = deleteViewModel;
    }

    @Override
    public void prepareSuccessView() {
        // 更新 ViewModel 的状态
        deleteViewModel.setDeleteState(DeleteState.SUCCESS);

        // 提示用户删除成功
        JOptionPane.showMessageDialog(null, "Recipe deleted successfully!");
    }

    @Override
    public void prepareFailureView() {
        // 更新 ViewModel 的状态
        deleteViewModel.setDeleteState(DeleteState.FAILURE);

        // 提示用户删除失败
        JOptionPane.showMessageDialog(null, "Failed to delete recipe. Recipe not found!");
    }
}

