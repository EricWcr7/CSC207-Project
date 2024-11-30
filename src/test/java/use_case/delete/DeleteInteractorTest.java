package use_case.delete;

import data_access.InMemoryUserDataAccessObject;
import data_access.RecipeDataAccessObject;
import entity.CommonRecipe;
import entity.CommonUser;
import entity.Recipe;
import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteInteractorTest {

    @Test
    void execute() {
        // 初始化测试依赖
        final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        final RecipeDataAccessObject recipeDataAccessObject = new RecipeDataAccessObject();

        String username = "testUser";
        String recipeName = "testRecipe";

        // 创建用户并保存
        CommonUser user = new CommonUser(username, "password");
        userDataAccessObject.save(user);
        userDataAccessObject.setCurrentUsername(username);

        // 创建菜肴并添加到用户的 createdRecipes 列表
        Recipe recipe = new CommonRecipe("1", recipeName, "category", "instructions", {"a", "a"} 0, 0);
        user.addCreatedRecipe(recipe);
        recipeDataAccessObject.saveRecipe(recipe);

        // 初始化 DeletePresenter
        DeleteOutputBoundary deletePresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                System.out.println("Delete operation succeeded.");
            }

            @Override
            public void prepareFailureView() {
                System.out.println("Delete operation failed.");
            }
        };

        // 初始化 DeleteInteractor
        DeleteInteractor deleteInteractor = new DeleteInteractor(deletePresenter, recipeDataAccessObject, userDataAccessObject);

        // 构造输入数据
        DeleteInputData inputData = new DeleteInputData(username, recipeName);

        // 执行删除操作
        deleteInteractor.execute(inputData);

        // 验证操作结果
        User updatedUser = userDataAccessObject.get(username);
        assertNotNull(updatedUser);

        assertFalse(recipeDataAccessObject.isNameInRecipes(recipeName), "Recipe should no longer exist in repository.");
    }

    @Test
    void executeWithNonExistingRecipe() {
        // 初始化测试依赖
        final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        final RecipeDataAccessObject recipeDataAccessObject = new RecipeDataAccessObject();

        String username = "testUser";
        String nonExistingRecipe = "nonExistingRecipe";

        // 创建用户并保存
        CommonUser user = new CommonUser(username, "password");
        userDataAccessObject.save(user);
        userDataAccessObject.setCurrentUsername(username);

        // 初始化 DeletePresenter
        DeleteOutputBoundary deletePresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                System.out.println("Delete operation succeeded.");
            }

            @Override
            public void prepareFailureView() {
                System.out.println("Delete operation failed.");
            }
        };

        // 初始化 DeleteInteractor
        DeleteInteractor deleteInteractor = new DeleteInteractor(deletePresenter, recipeDataAccessObject, userDataAccessObject);

        // 构造输入数据
        DeleteInputData inputData = new DeleteInputData(username, nonExistingRecipe);

        // 执行删除操作
        deleteInteractor.execute(inputData);

    }
}
