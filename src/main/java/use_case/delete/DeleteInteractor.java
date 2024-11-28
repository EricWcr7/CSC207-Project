package use_case.delete;

import java.util.List;

import data_access.RecipeDataAccessObject;
import entity.Recipe;

/**
 * Interactor class for the Delete Use Case.
 * Handles the business logic for deleting a recipe.
 */
public class DeleteInteractor implements DeleteInputBoundary {

    private final DeleteOutputBoundary deletePresenter;
    private final RecipeDataAccessObject recipeDataAccessObject;

    public DeleteInteractor(DeleteOutputBoundary deletePresenter, RecipeDataAccessObject recipeDataAccessObject) {
        this.deletePresenter = deletePresenter;
        this.recipeDataAccessObject = recipeDataAccessObject;
    }

    @Override
    public void execute(DeleteInputData inputData) {
        final String recipeName = inputData.getRecipeName();

        final boolean localDeleted = recipeDataAccessObject.removeRecipeFromLocalFile("new_recipes.json", recipeName);
        if (!localDeleted) {
            deletePresenter.prepareFailureView();

        }

        recipeDataAccessObject.loadRecipesFromCloud();

        if (recipeDataAccessObject.isNameInRecipes(recipeName)) {
            recipeDataAccessObject.removeRecipeByName(recipeName);

            final List<Recipe> updatedRecipes = recipeDataAccessObject.getCachedRecipes();
            recipeDataAccessObject.writeRecipesToFile(updatedRecipes);

            recipeDataAccessObject.deleteFileFromFileIo();
            recipeDataAccessObject.uploadFileToFileIo();

            deletePresenter.prepareSuccessView();
        }
        else {
            deletePresenter.prepareFailureView();
        }
    }
}
