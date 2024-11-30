package use_case.delete;

import java.util.List;

import data_access.RecipeDataAccessObject;
import entity.Recipe;

/**
 * Interactor class for the Delete Use Case.
 * Handles the business logic for deleting a recipe.
 */
public class DeleteInteractor implements DeleteInputBoundary {

    // Presenter for handling output logic (e.g., showing success or failure messages)
    private final DeleteOutputBoundary deletePresenter;

    // DAO for interacting with recipe data storage (e.g., local and cloud JSON files)
    private final DeleteDataAccessInterface recipeDataAccessObject;

    private final DUserDataAccessInterface deleteUserDataAccessObject;

    /**
     * Constructor for DeleteInteractor.
     *
     * @param deletePresenter         The output boundary (presenter) for the Delete Use Case.
     * @param recipeDataAccessObject  The data access object for interacting with recipes.
     */
    public DeleteInteractor(DeleteOutputBoundary deletePresenter, RecipeDataAccessObject recipeDataAccessObject, DUserDataAccessInterface deleteUserDataAccessObject) {
        this.deletePresenter = deletePresenter;
        // Assign presenter for handling output
        this.recipeDataAccessObject = recipeDataAccessObject;
        // Assign DAO for data operations
        this.deleteUserDataAccessObject = deleteUserDataAccessObject;
    }

    @Override
    public void execute(DeleteInputData inputData) {
        String recipeName = inputData.getRecipeName();
        String username = inputData.getUsername();

        // Step 1: 删除用户创建的菜肴
        boolean userRecipeDeleted = deleteUserDataAccessObject.removeUserCreatedRecipe(username, recipeName);

        if (!userRecipeDeleted) {
            deletePresenter.prepareFailureView();
            return;
        }

        // Step 2: 删除 all_recipes.json 中的菜肴
        boolean localDeleted = recipeDataAccessObject.removeRecipeFromLocalFile("new_recipes.json", recipeName);
        if (!localDeleted) {
            deletePresenter.prepareFailureView();
            return;
        }

        // Step 3: 从云端删除菜肴
        recipeDataAccessObject.loadRecipesFromCloud();
        if (recipeDataAccessObject.isNameInRecipes(recipeName)) {
            recipeDataAccessObject.removeRecipeByName(recipeName);

            // 更新云端文件
            List<Recipe> updatedRecipes = recipeDataAccessObject.getCachedRecipes();
            recipeDataAccessObject.writeRecipesToFile(updatedRecipes);
            recipeDataAccessObject.deleteFileFromFileIo();
            recipeDataAccessObject.uploadFileToFileIo();

            // 通知 Presenter 删除成功
            deletePresenter.prepareSuccessView();
        } else {
            deletePresenter.prepareFailureView();
        }
    }

}

