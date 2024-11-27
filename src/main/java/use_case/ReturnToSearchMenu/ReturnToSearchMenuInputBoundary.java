package use_case.ReturnToSearchMenu;

public interface ReturnToSearchMenuInputBoundary {

    // void execute(ReturnToSearchMenuInputData returnToSearchMenuInputData);

    void fromEditRecipeBackToSearchMenu();
    void fromFavoriteRecipeBackToSearchMenu();

    void fromShoppingListBackToSearchMenu();

    void fromDisplayBackToSearchMenu();

    void fromChooseBackToSearchMenu();
}
