package use_case.recipe_search;

/**
 * Input Boundary for actions related to recipe search.
 */
public interface RecipeSearchInputBoundary {

    /**
     * Executes the recipe search use case.
     * @param recipeSearchInputData the input data
     */
    void execute(RecipeSearchInputData recipeSearchInputData);

    void switchToFavoriteRecipeView();

    void switchToEditView();
}


