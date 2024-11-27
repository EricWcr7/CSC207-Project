package interface_adapter.delete;

import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInputData;

public class DeleteController {
    private final DeleteInputBoundary deleteInputBoundary;


    public DeleteController(DeleteInputBoundary deleteInputBoundary) {
        this.deleteInputBoundary = deleteInputBoundary;
    }


    public void deleteRecipe(String recipeName) {
        if (recipeName == null || recipeName.isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be null or empty.");
        }


        DeleteInputData inputData = new DeleteInputData(recipeName);


        deleteInputBoundary.execute(inputData);
    }
}

