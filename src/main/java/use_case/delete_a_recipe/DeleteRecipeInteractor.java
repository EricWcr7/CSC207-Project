package use_case.delete_a_recipe;

import data_access.RecipeDataAccessObject;

public class DeleteRecipeInteractor implements DeleteRecipeInputBoundary {
    private final RecipeDataAccessObject recipeDataAccessObject;
    private final DeleteRecipeOutputBoundary outputBoundary;

    public DeleteRecipeInteractor(RecipeDataAccessObject recipeDataAccessObject, DeleteRecipeOutputBoundary outputBoundary) {
        this.recipeDataAccessObject = recipeDataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(DeleteRecipeInputData inputData) {
        String dishName = inputData.getDishName();

        boolean success = recipeDataAccessObject.deleteRecipe(dishName);
        DeleteRecipeOutputData outputData = new DeleteRecipeOutputData(dishName, success);

        outputBoundary.prepareSuccessView(outputData);
    }
}

