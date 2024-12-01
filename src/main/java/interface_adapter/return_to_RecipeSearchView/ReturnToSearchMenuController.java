package interface_adapter.return_to_RecipeSearchView;

import use_case.return_to_RecipeSearchView.ReturnToSearchMenuInputBoundary;

public class ReturnToSearchMenuController {

    private ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor;

    public ReturnToSearchMenuController(ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor) {
        this.returnToSearchMenuInteractor = returnToSearchMenuInteractor;
    }

//    public void execute(String searchKeyword, String username, String[] favoriteRecipes) {
//        final ReturnToSearchMenuInputData returnToSearchMenuInputData = new ReturnToSearchMenuInputData(
//                searchKeyword, username, favoriteRecipes);
//        returnToSearchMenuInteractor.execute(returnToSearchMenuInputData);
//    }

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
//        final ReturnToSearchMenuInputData returnToSearchMenuInputData = new ReturnToSearchMenuInputData(
//                "", username, favoriteRecipes);
        returnToSearchMenuInteractor.fromFavoriteRecipeBackToSearchMenu();
    }

    public void fromShoppingListBackToSearchMenu() {
//        final ReturnToSearchMenuInputData returnToSearchMenuInputData = new ReturnToSearchMenuInputData(
//                "", username, favoriteRecipes);
        returnToSearchMenuInteractor.fromShoppingListBackToSearchMenu();
    }
}
