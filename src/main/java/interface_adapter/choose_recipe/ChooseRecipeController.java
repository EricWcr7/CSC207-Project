package interface_adapter.choose_recipe;

import use_case.choose_recipe.ChooseRecipeInputBoundary;
import use_case.choose_recipe.ChooseRecipeInputData;


import java.util.List;

public class ChooseRecipeController {

    private final ChooseRecipeInputBoundary chooseRecipeInteractor;

    public ChooseRecipeController(ChooseRecipeInputBoundary chooseRecipeInteractor) {
        this.chooseRecipeInteractor = chooseRecipeInteractor;
    }

    /**
     * Executes the Recipe Choose Use Case.
     * @param searchDishName the keyword user types to search for
     */
    public void execute(String searchDishName) {
        // Create the input data for the search operation
        final ChooseRecipeInputData chooseRecipeInputData = new ChooseRecipeInputData(searchDishName);

        // Perform the choose dish through the interactor
        chooseRecipeInteractor.execute(chooseRecipeInputData);

        System.out.println("Select with dish name: " + searchDishName);
    }


//    /**
//     * Sets the selected recipe name in the ViewModel.
//     * @param recipeName the name of the selected recipe
//     */
//    public void selectRecipe(String recipeName) {
//        viewModel.getState().setSearchKeyword(recipeName);  // Update view model state directly
//        viewModel.firePropertyChanged(); // Notify the view of changes
//    }
//
//    /**
//     * Updates the list of recipe names in the ViewModel.
//     * @param recipeNames the list of recipe names to display
//     */
//    public void updateRecipeList(List<String> recipeNames) {
//        viewModel.getState().setRecipeNames(recipeNames);
//        viewModel.firePropertyChanged();
//    }
//
//    /**
//     * Clears the selection or navigates back to the search view.
//     */
//    public void clearSelection() {
//        viewModel.getState().setSearchKeyword("");  // Clear any selected recipe
//        viewModel.firePropertyChanged();
//    }
}

