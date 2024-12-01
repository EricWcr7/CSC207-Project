package use_case.return_to_recipe_search_view;

import use_case.favorite_receipe.FavoriteRecipeDataAccessInterface;
import use_case.shopping_list.ShoppingListUserDataAccessInterface;

/**
 * The ReturnToSearchMenu Interactor.
 */
public class ReturnToSearchMenuInteractor implements ReturnToSearchMenuInputBoundary {
    private ReturnToSearchMenuOutputBoundary returnToSearchMenuPresenter;
    private final FavoriteRecipeDataAccessInterface favoriteRecipeDataAccessObject;
    private final ShoppingListUserDataAccessInterface shoppingListDataAccessObject;

    public ReturnToSearchMenuInteractor(ReturnToSearchMenuOutputBoundary returnToSearchMenuPresenter,
                                        FavoriteRecipeDataAccessInterface favoriteRecipeDataAccessObject,
                                        ShoppingListUserDataAccessInterface shoppingListDataAccessObject) {
        this.returnToSearchMenuPresenter = returnToSearchMenuPresenter;
        this.favoriteRecipeDataAccessObject = favoriteRecipeDataAccessObject;
        this.shoppingListDataAccessObject = shoppingListDataAccessObject;
    }

    /**
     * Handles the transition from the Display View back to the Search Menu.
     * This method delegates the responsibility of managing the transition
     * to the returnToSearchMenuPresenter.
     */
    @Override
    public void fromDisplayBackToSearchMenu() {
        returnToSearchMenuPresenter.fromDisplayBackToSearchMenu();
    }

    /**
     * Handles the transition from the Choose View back to the Search Menu.
     * This method delegates the responsibility of managing the transition
     * to the returnToSearchMenuPresenter.
     */
    @Override
    public void fromChooseBackToSearchMenu() {
        returnToSearchMenuPresenter.fromChooseBackToSearchMenu();
    }

    /**
     * Handles the transition from the Edit Recipe View back to the Search Menu.
     * This method delegates the responsibility of managing the transition
     * to the returnToSearchMenuPresenter.
     */
    @Override
    public void fromEditRecipeBackToSearchMenu() {
        returnToSearchMenuPresenter.fromEditRecipeBackToSearchMenu();
    }

    /**
     * Handles the transition from the Favorite Recipe View back to the Search Menu.
     * This method delegates the responsibility of managing the transition
     * to the returnToSearchMenuPresenter.
     */
    @Override
    public void fromFavoriteRecipeBackToSearchMenu() {
        returnToSearchMenuPresenter.fromFavoriteRecipeBackToSearchMenu();
    }

    /**
     * Handles the transition from the Shopping List View back to the Search Menu.
     * This method delegates the responsibility of managing the transition
     * to the returnToSearchMenuPresenter.
     */
    @Override
    public void fromShoppingListBackToSearchMenu() {
        returnToSearchMenuPresenter.fromShoppingListBackToSearchMenu();
    }

}
