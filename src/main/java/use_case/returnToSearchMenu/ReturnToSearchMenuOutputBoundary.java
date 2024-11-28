package use_case.returnToSearchMenu;

/**
 * The output boundary for the ReturnToSearchMenu Use Case.
 */
public interface ReturnToSearchMenuOutputBoundary {

    // public void prepareSuccessView(ReturnToSearchMenuOutputData recipeSearchOutputData);
    /**
     * Execute the fromEditRecipeBackToSearchMenu view for the ReturnToSearchMenu Use Case.
     */
    void fromEditRecipeBackToSearchMenu();

    /**
     * Execute the fromFavoriteRecipeBackToSearchMenu view for the ReturnToSearchMenu Use Case.
     */
    void fromFavoriteRecipeBackToSearchMenu();

    /**
     * Execute the fromShoppingListBackToSearchMenu view for the ReturnToSearchMenu Use Case.
     */
    void fromShoppingListBackToSearchMenu();

    /**
     * Execute the fromDisplayBackToSearchMenu view for the ReturnToSearchMenu Use Case.
     */
    void fromDisplayBackToSearchMenu();

    /**
     * Execute the fromChooseBackToSearchMenu view for the ReturnToSearchMenu Use Case.
     */
    void fromChooseBackToSearchMenu();
}
