package use_case.return_to_RecipeSearchView;

/**
 * Input Boundary for actions which are related to favoriteRecipe.
 */
public interface ReturnToSearchMenuInputBoundary {

    // void execute(ReturnToSearchMenuInputData returnToSearchMenuInputData);
    /**
     * Executes the ReturnToSearchMenu use case.
     */
    void fromEditRecipeBackToSearchMenu();

    /**
     * Executes the ReturnToSearchMenu use case.
     */
    void fromFavoriteRecipeBackToSearchMenu();

    /**
     * Executes the ReturnToSearchMenu use case.
     */
    void fromShoppingListBackToSearchMenu();

    /**
     * Executes the ReturnToSearchMenu use case.
     */
    void fromDisplayBackToSearchMenu();

    /**
     * Executes the ReturnToSearchMenu use case.
     */
    void fromChooseBackToSearchMenu();
}
