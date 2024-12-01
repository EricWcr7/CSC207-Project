package interface_adapter.return_to_recipe_search_view;

import use_case.return_to_recipe_search_view.ReturnToSearchMenuInputBoundary;

public class ReturnToSearchMenuController {

    private ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor;

    public ReturnToSearchMenuController(ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor) {
        this.returnToSearchMenuInteractor = returnToSearchMenuInteractor;
    }

    public void fromDisplayBackToSearchMenu() {
        returnToSearchMenuInteractor.fromDisplayBackToSearchMenu();
    }

    public void fromChooseRecipeBackToSearchMenu() {
        returnToSearchMenuInteractor.fromChooseBackToSearchMenu();
    }

    public void fromEditRecipeBackToSearchMenu() {
        returnToSearchMenuInteractor.fromEditRecipeBackToSearchMenu();
    }

    public void fromFavoriteRecipeBackToSearchMenu() {
        returnToSearchMenuInteractor.fromFavoriteRecipeBackToSearchMenu();
    }

    public void fromShoppingListBackToSearchMenu() {
        returnToSearchMenuInteractor.fromShoppingListBackToSearchMenu();
    }
}
