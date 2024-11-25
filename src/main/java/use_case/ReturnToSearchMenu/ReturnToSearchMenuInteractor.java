package use_case.ReturnToSearchMenu;

import use_case.recipe_search.RecipeSearchOutputData;

import java.util.Arrays;

public class ReturnToSearchMenuInteractor implements ReturnToSearchMenuInputBoundary {
    private ReturnToSearchMenuOutputBoundary returnToSearchMenuPresenter;

    public ReturnToSearchMenuInteractor(ReturnToSearchMenuOutputBoundary returnToSearchMenuPresenter) {
        this.returnToSearchMenuPresenter = returnToSearchMenuPresenter;
    }

    @Override
    public void execute(ReturnToSearchMenuInputData returnToSearchMenuInputData) {
        final String username = returnToSearchMenuInputData.getUsername();
        final String[] favoriteRecipes = returnToSearchMenuInputData.getFavoriteRecipes();
        System.out.println("Current logged in account: " + username);
        System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));
        final ReturnToSearchMenuOutputData recipeSearchOutputData = new ReturnToSearchMenuOutputData(
                returnToSearchMenuInputData.getSearchKeyword(), username, favoriteRecipes);
        returnToSearchMenuPresenter.prepareSuccessView(recipeSearchOutputData);
    }

    @Override
    public void fromEditRecipeBackToSearchMenu() {
        returnToSearchMenuPresenter.fromEditRecipeBackToSearchMenu();
    }

    @Override
    public void fromFavoriteRecipeBackToSearchMenu(ReturnToSearchMenuInputData returnToSearchMenuInputData) {
        final String username = returnToSearchMenuInputData.getUsername();
        final String[] favoriteRecipes = returnToSearchMenuInputData.getFavoriteRecipes();
        System.out.println("Current logged in account: " + username);
        System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));
        final ReturnToSearchMenuOutputData recipeSearchOutputData = new ReturnToSearchMenuOutputData(
                returnToSearchMenuInputData.getSearchKeyword(), username, favoriteRecipes);
        returnToSearchMenuPresenter.fromFavoriteRecipeBackToSearchMenu(recipeSearchOutputData);
    }
}
