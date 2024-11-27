package interface_adapter.delete;

import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInputData;

public class DeleteController {
    private final DeleteInputBoundary deleteInputBoundary;

    // 构造方法，注入用例层接口
    public DeleteController(DeleteInputBoundary deleteInputBoundary) {
        this.deleteInputBoundary = deleteInputBoundary;
    }

    // 删除菜谱的方法
    public void deleteRecipe(String recipeName) {
        if (recipeName == null || recipeName.isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be null or empty.");
        }

        // 封装输入数据
        DeleteInputData inputData = new DeleteInputData(recipeName);

        // 调用用例层逻辑
        deleteInputBoundary.execute(inputData);
    }
}

