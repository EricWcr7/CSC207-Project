package interface_adapter.delete_a_recipe;

import use_case.delete_a_recipe.DeleteRecipeOutputBoundary;
import use_case.delete_a_recipe.DeleteRecipeOutputData;

public class DeleteRecipePresenter implements DeleteRecipeOutputBoundary {

    @Override
    public void prepareSuccessView(DeleteRecipeOutputData outputData) {
        if (outputData.isSuccess()) {
            System.out.println("Recipe \"" + outputData.getDishName() + "\" successfully deleted.");
        } else {
            System.out.println("Failed to delete recipe \"" + outputData.getDishName() + "\".");
        }
    }
}

