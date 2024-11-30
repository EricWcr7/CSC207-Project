package interface_adapter.delete;

import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInputData;

public class DeleteController {
    private final DeleteInputBoundary deleteInputBoundary;

    public DeleteController(DeleteInputBoundary deleteInputBoundary) {
        this.deleteInputBoundary = deleteInputBoundary;
    }

    public void deleteRecipe(String recipeName) {
        if (recipeName == null || recipeName.isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be null or empty.");
        }

        // 从 Session 获取当前用户的用户名
        String username = util.Session.getCurrentUser().getName();
        if (username == null || username.isEmpty()) {
            throw new IllegalStateException("No user is currently logged in.");
        }

        // 创建包含用户名和菜谱名的 DeleteInputData
        DeleteInputData inputData = new DeleteInputData(username, recipeName);

        // 调用输入边界执行删除逻辑
        deleteInputBoundary.execute(inputData);
    }
}
