package use_case.ReturnToSearchMenu;

public interface ReturnToSearchMenuInputBoundary {

    void execute(ReturnToSearchMenuInputData returnToSearchMenuInputData);
    void fromEditRecipeBackToSearchMenu();
    void fromFavoriteRecipeBackToSearchMenu(ReturnToSearchMenuInputData returnToSearchMenuInputData);
}
