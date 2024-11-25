package use_case.ReturnToSearchMenu;

public interface ReturnToSearchMenuOutputBoundary {

    public void prepareSuccessView();

    void fromEditRecipeBackToSearchMenu();
    void fromFavoriteRecipeBackToSearchMenu();
    void fromDeleteRecipeBackToSearchMenu();
}
