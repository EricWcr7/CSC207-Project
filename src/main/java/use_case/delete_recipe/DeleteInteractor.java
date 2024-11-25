package use_case.delete_recipe;

import data_access.RecipeDataAccessObject;

public class DeleteInteractor implements DeleteInputBoundary {
    private final RecipeDataAccessObject recipeDAO;
    private final DeleteOutputBoundary outputBoundary;

    public DeleteInteractor(RecipeDataAccessObject recipeDataAccessObject, DeleteOutputBoundary deletePresenter) {
        this.recipeDAO = recipeDataAccessObject;
        this.outputBoundary = deletePresenter;
    }

    @Override
    public DeleteOutputData deleteRecipe(DeleteInputData inputData) {
        boolean isDeleted = recipeDAO.deleteRecipeById(inputData.getRecipeId());
        DeleteOutputData outputData;
        if (isDeleted) {
            outputData = new DeleteOutputData(true, "Recipe successfully deleted.");
        } else {
            outputData = new DeleteOutputData(false, "Recipe not found or could not be deleted.");
        }
        outputBoundary.present(outputData);
        return outputData;
    }
}

