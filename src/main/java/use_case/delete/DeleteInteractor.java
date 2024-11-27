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
        String recipeName = inputData.getRecipeName();


        boolean localDeleted = recipeDataAccessObject.removeRecipeFromLocalFile("new_recipes.json", recipeName);


        recipeDataAccessObject.loadRecipesFromCloud();
        if (recipeDataAccessObject.isNameInRecipes(recipeName)) {

            recipeDataAccessObject.removeRecipeByName(recipeName);

            List<Recipe> updatedRecipes = recipeDataAccessObject.getCachedRecipes();
            recipeDataAccessObject.writeRecipesToFile(updatedRecipes);
            recipeDataAccessObject.deleteFileFromFileIo();
            recipeDataAccessObject.uploadFileToFileIo();

            deletePresenter.prepareSuccessView();
        } else {

            deletePresenter.prepareFailureView();
        }
    }
}

