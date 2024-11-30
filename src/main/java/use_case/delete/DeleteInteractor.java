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

            deleteDataAccess.deleteRecipeFromAllRecipes(inputData.getRecipeName());

            deleteOutputBoundary.prepareSuccessView();
        }
        catch (Exception e) {

            deleteOutputBoundary.prepareFailureView();
        }
    }

    @Override
    public void deleteUserRecipe(DeleteInputData inputData) {
        try {

            String username = Session.getCurrentUser().getName();

            deleteUserDataAccess.deleteRecipeForUser(username, inputData.getRecipeName());

            deleteOutputBoundary.prepareSuccessView();
        }
        catch (Exception e) {

            deleteOutputBoundary.prepareFailureView();
        }
    }
}
