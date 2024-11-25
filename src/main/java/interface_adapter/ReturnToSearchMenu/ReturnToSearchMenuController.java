package interface_adapter.ReturnToSearchMenu;

import use_case.ReturnToSearchMenu.ReturnToSearchMenuInputBoundary;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuInputData;

public class ReturnToSearchMenuController {

    private ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor;

    public ReturnToSearchMenuController(ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor) {
        this.returnToSearchMenuInteractor = returnToSearchMenuInteractor;
    }

    public void execute() {
        returnToSearchMenuInteractor.execute();
    }

    public void fromEditRecipeBackToSearchMenu() {
        returnToSearchMenuInteractor.fromEditRecipeBackToSearchMenu();
    }

    public void fromFavoriteRecipeBackToSearchMenu() {
        returnToSearchMenuInteractor.fromFavoriteRecipeBackToSearchMenu();
    }

    public void fromDeleteRecipeBackToSearchMenu() {
        returnToSearchMenuInteractor.fromDeleteRecipeBackToSearchMenu();
    }
}
