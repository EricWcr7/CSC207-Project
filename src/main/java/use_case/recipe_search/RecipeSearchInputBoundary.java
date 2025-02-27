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

    /**
     * Executes the switchToFavoriteRecipeView of recipe search use case.
     * @param recipeSearchInputData the input data
     */
    void switchToFavoriteRecipeView(RecipeSearchInputData recipeSearchInputData);

    /**
     * Executes the switchToEditView of recipe search use case.
     */
    void switchToEditView();

    /**
     * Fetches all recipes from the API and stores them to the shared file for global access.
     * Should be called once to initialize the recipe storage.
     */
    void initializeRecipeStorage();
}

