package interface_adapter.delete_a_recipe;

import use_case.delete_a_recipe.DeleteRecipeInputBoundary;
import use_case.delete_a_recipe.DeleteRecipeInputData;

public class DeleteRecipeController {
    private final DeleteRecipeInputBoundary deleteRecipeInputBoundary;

    public DeleteRecipeController(DeleteRecipeInputBoundary deleteRecipeInteractor) {
        this.deleteRecipeInputBoundary = deleteRecipeInteractor;
    }

    public void execute(String dishName) {
        DeleteRecipeInputData deleteData = new DeleteRecipeInputData(dishName);
        deleteRecipeInputBoundary.execute(deleteData);
    }
}

