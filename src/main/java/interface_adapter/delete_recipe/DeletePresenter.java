package interface_adapter.delete_recipe;

import use_case.delete_recipe.DeleteOutputBoundary;
import use_case.delete_recipe.DeleteOutputData;

public class DeletePresenter implements DeleteOutputBoundary {
    @Override
    public void present(DeleteOutputData outputData) {
        if (outputData.isSuccess()) {
            System.out.println(outputData.getMessage());
        } else {
            System.err.println(outputData.getMessage());
        }
    }
}

