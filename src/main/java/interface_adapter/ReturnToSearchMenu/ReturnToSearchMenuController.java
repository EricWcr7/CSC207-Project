package interface_adapter.ReturnToSearchMenu;

import use_case.ReturnToSearchMenu.ReturnToSearchMenuInputBoundary;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuInputData;
import use_case.choose_recipe.ChooseRecipeInputData;

public class ReturnToSearchMenuController {

    private ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor;

    public ReturnToSearchMenuController(ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor) {
        this.returnToSearchMenuInteractor = returnToSearchMenuInteractor;
    }

    public void execute(String searchKeyword, String username, String[] favoriteRecipes) {
        final ReturnToSearchMenuInputData returnToSearchMenuInputData = new ReturnToSearchMenuInputData(
                searchKeyword, username, favoriteRecipes);
        returnToSearchMenuInteractor.execute(returnToSearchMenuInputData);
    }

    public void fromEditRecipeBackToSearchMenu() {
        returnToSearchMenuInteractor.fromEditRecipeBackToSearchMenu();
    }

    public void fromFavoriteRecipeBackToSearchMenu() {
        returnToSearchMenuInteractor.fromFavoriteRecipeBackToSearchMenu();
    }
}
