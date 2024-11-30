package use_case.delete;

import util.Session;

public class DeleteInteractor implements DeleteInputBoundary {

    private final DeleteDataAccessInterface deleteDataAccess;
    private final DeleteUserDataAccessInterface deleteUserDataAccess;
    private final DeleteOutputBoundary deleteOutputBoundary;

    // 构造方法
    public DeleteInteractor(DeleteDataAccessInterface deleteDataAccess,
                            DeleteUserDataAccessInterface deleteUserDataAccess,
                            DeleteOutputBoundary deleteOutputBoundary) {
        this.deleteDataAccess = deleteDataAccess;
        this.deleteUserDataAccess = deleteUserDataAccess;
        this.deleteOutputBoundary = deleteOutputBoundary;
    }

    @Override
    public void deleteRecipe(DeleteInputData inputData) {
        try {
            // 从 all_recipes.json 中删除菜肴
            deleteDataAccess.deleteRecipeFromAllRecipes(inputData.getRecipeName());
            // 删除成功，调用成功回调
            deleteOutputBoundary.prepareSuccessView();
        } catch (Exception e) {
            // 删除失败，调用失败回调
            deleteOutputBoundary.prepareFailureView();
        }
    }

    @Override
    public void deleteUserRecipe(DeleteInputData inputData) {
        try {
            // 获取当前用户的用户名
            String username = Session.getCurrentUser().getName();
            // 调用 deleteUserDataAccess 来删除用户创建的菜肴
            deleteUserDataAccess.deleteRecipeForUser(username, inputData.getRecipeName());
            // 删除成功，调用成功回调
            deleteOutputBoundary.prepareSuccessView();
        } catch (Exception e) {
            // 删除失败，调用失败回调
            deleteOutputBoundary.prepareFailureView();
        }
    }
}
