package use_case.ReturnToSearchMenu;

public interface ReturnToSearchMenuOutputBoundary {

    public void prepareSuccessView(ReturnToSearchMenuOutputData recipeSearchOutputData);

    void fromEditRecipeBackToSearchMenu();
    void fromFavoriteRecipeBackToSearchMenu();
}
