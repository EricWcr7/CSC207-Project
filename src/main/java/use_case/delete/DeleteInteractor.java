package use_case.delete;

import data_access.RecipeDataAccessObject;
import entity.Recipe;

import java.util.List;

public class DeleteInteractor implements DeleteInputBoundary {
    private final DeleteOutputBoundary deletePresenter;
    private final RecipeDataAccessObject recipeDataAccessObject;

    public DeleteInteractor(DeleteOutputBoundary deletePresenter, RecipeDataAccessObject recipeDataAccessObject) {
        this.deletePresenter = deletePresenter;
        this.recipeDataAccessObject = recipeDataAccessObject;
    }

    @Override
    public void execute(DeleteInputData inputData) {
        String recipeName = inputData.getRecipeName(); // 获取需要删除的菜名

        // Step 1: 删除本地文件中的菜谱
        boolean localDeleted = recipeDataAccessObject.removeRecipeFromLocalFile("new_recipes.json", recipeName);

        // Step 2: 检查云端是否存在菜谱
        recipeDataAccessObject.loadRecipesFromCloud();
        if (recipeDataAccessObject.isNameInRecipes(recipeName)) {
            // Step 3: 删除云端缓存的菜谱
            recipeDataAccessObject.removeRecipeByName(recipeName);

            // Step 4: 更新 JSON 文件并上传到云端
            List<Recipe> updatedRecipes = recipeDataAccessObject.getCachedRecipes();
            recipeDataAccessObject.writeRecipesToFile(updatedRecipes);
            recipeDataAccessObject.deleteFileFromFileIo();
            recipeDataAccessObject.uploadFileToFileIo();

            // Step 5: 调用 Presenter 返回成功信息
            deletePresenter.prepareSuccessView();
        } else {
            // 如果云端没有找到菜谱，调用失败视图
            deletePresenter.prepareFailureView();
        }
    }
}

