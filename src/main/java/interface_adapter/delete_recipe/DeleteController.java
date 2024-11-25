package interface_adapter.delete_recipe;

import use_case.delete_recipe.DeleteInputBoundary;
import use_case.delete_recipe.DeleteInputData;
import use_case.delete_recipe.DeleteOutputData;

public class DeleteController {
    private final DeleteInputBoundary deleteInteractor;

    public DeleteController(DeleteInputBoundary deleteInteractor) {
        this.deleteInteractor = deleteInteractor;
    }

    public DeleteOutputData deleteRecipe(String recipeId) {
        DeleteInputData deleteInputData = new DeleteInputData(recipeId);
        return deleteInteractor.deleteRecipe(deleteInputData);
    }
}

