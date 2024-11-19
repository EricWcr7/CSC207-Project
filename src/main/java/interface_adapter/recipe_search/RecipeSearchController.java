package interface_adapter.recipe_search;

import use_case.recipe_search.RecipeSearchInputBoundary;
import use_case.recipe_search.RecipeSearchInputData;

/**
 * Controller for the RecipeSearch Use Case.
 */
public class RecipeSearchController {
    private final RecipeSearchInputBoundary recipeSearchInteractor;

    public RecipeSearchController(RecipeSearchInputBoundary recipeSearchInteractor) {
        this.recipeSearchInteractor = recipeSearchInteractor;
    }


    /**
     * Executes the Recipe Search Use Case.
     * @param searchKeyword the keyword user types to search for
     */
    public void execute(String searchKeyword) {
        // Create the input data for the search operation
        final RecipeSearchInputData recipeSearchInputData = new RecipeSearchInputData(searchKeyword);

        // Perform the search operation through the interactor
        recipeSearchInteractor.execute(recipeSearchInputData);

        System.out.println("Search button clicked with keyword: " + searchKeyword);
    }

    public void switchToFavoriteRecipeView() {
        recipeSearchInteractor.switchToFavoriteRecipeView();
    }

    public void switchToEditView() {
        recipeSearchInteractor.switchToEditView();
        System.out.println("RecipeSearchController work");
    }
}


