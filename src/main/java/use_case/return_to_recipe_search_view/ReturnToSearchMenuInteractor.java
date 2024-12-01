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

    //    @Override
    //    public void execute(ReturnToSearchMenuInputData returnToSearchMenuInputData) {
    //        final String username = returnToSearchMenuInputData.getUsername();
    //        final String[] favoriteRecipes = returnToSearchMenuInputData.getFavoriteRecipes();
    //        System.out.println("Current logged in account: " + username);
    //        System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));
    //        final ReturnToSearchMenuOutputData recipeSearchOutputData = new ReturnToSearchMenuOutputData(
    //                returnToSearchMenuInputData.getSearchKeyword(), username, favoriteRecipes);
    //        returnToSearchMenuPresenter.prepareSuccessView(recipeSearchOutputData);
    //    }

    @Override
    public void fromDisplayBackToSearchMenu() {
        returnToSearchMenuPresenter.fromDisplayBackToSearchMenu();
    }

    @Override
    public void fromChooseBackToSearchMenu() {
        returnToSearchMenuPresenter.fromChooseBackToSearchMenu();

    }

    @Override
    public void fromEditRecipeBackToSearchMenu() {
        returnToSearchMenuPresenter.fromEditRecipeBackToSearchMenu();
    }

    @Override
    public void fromFavoriteRecipeBackToSearchMenu() {
        //        final String username = returnToSearchMenuInputData.getUsername();
        //        final String[] favoriteRecipes = returnToSearchMenuInputData.getFavoriteRecipes();
        //        System.out.println("Current logged in account: " + username);
        //        System.out.println("Current favoriteRecipe in account: "
        //        + Arrays.toString(favoriteRecipes));
        //        System.out.println("Current account in InMemoryUserDataAccessObject: "
        //        + favoriteRecipeDataAccessObject.get(username).getName());
        //        System.out.println("Current favoriteRecipes in InMemoryUserDataAccessObject: "
        //        + Arrays.toString(favoriteRecipeDataAccessObject.get(username).getFavoriteRecipes()));
        //        final ReturnToSearchMenuOutputData recipeSearchOutputData = new ReturnToSearchMenuOutputData(
        //                returnToSearchMenuInputData.getSearchKeyword(), username, favoriteRecipes);
        returnToSearchMenuPresenter.fromFavoriteRecipeBackToSearchMenu();
    }

    @Override
    public void fromShoppingListBackToSearchMenu() {
        //        final String username = returnToSearchMenuInputData.getUsername();
        //        final String[] favoriteRecipes = returnToSearchMenuInputData.getFavoriteRecipes();
        //        System.out.println("Current logged in account: " + username);
        //        System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));
        //        System.out.println("Current account in InMemoryUserDataAccessObject: "
        //        + shoppingListDataAccessObject.get(username).getName());
        //        System.out.println("Current favoriteRecipes in InMemoryUserDataAccessObject: "
        //        + Arrays.toString(shoppingListDataAccessObject.get(username).getFavoriteRecipes()));
        //        final ReturnToSearchMenuOutputData recipeSearchOutputData = new ReturnToSearchMenuOutputData(
        //                returnToSearchMenuInputData.getSearchKeyword(), username, favoriteRecipes);
        returnToSearchMenuPresenter.fromShoppingListBackToSearchMenu();
    }
}
