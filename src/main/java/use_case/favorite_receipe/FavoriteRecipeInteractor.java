package use_case.favorite_receipe;

import java.util.Arrays;

import entity.User;
import use_case.shopping_list.ShoppingListUserDataAccessInterface;

/**
 * The FavoriteRecipe Interactor.
 */
public class FavoriteRecipeInteractor implements FavoriteRecipeInputBoundary {
    private final FavoriteRecipeOutputBoundary favoriteRecipePresenter;
    private final FavoriteRecipeDataAccessInterface favoriteRecipeDataAccessObject;
    private final ShoppingListUserDataAccessInterface inMemoryUserDataAccessObject;

    public FavoriteRecipeInteractor(FavoriteRecipeOutputBoundary favoriteRecipePresenter,
                                    FavoriteRecipeDataAccessInterface favoriteRecipeDataAccessObject,
                                    ShoppingListUserDataAccessInterface inMemoryUserDataAccessObject) {
        this.favoriteRecipePresenter = favoriteRecipePresenter;
        this.favoriteRecipeDataAccessObject = favoriteRecipeDataAccessObject;
        this.inMemoryUserDataAccessObject = inMemoryUserDataAccessObject;
    }

    @Override
    public void execute(FavoriteRecipeInputData favoriteRecipeInputData) {
        final String username = favoriteRecipeInputData.getUsername();
        final String[] favoriteRecipes = favoriteRecipeInputData.getRecipeNames();
        final User user = favoriteRecipeDataAccessObject.get(username);
        user.setFavoriteRecipes(favoriteRecipes);
        favoriteRecipeDataAccessObject.updateUserFavoriteRecipes(user);
        System.out.println("Current account in InMemoryUserDataAccessObject: "
                + favoriteRecipeDataAccessObject.get(username).getName());
        System.out.println("Current favoriteRecipes in InMemoryUserDataAccessObject: "
                + Arrays.toString(favoriteRecipeDataAccessObject.get(username).getFavoriteRecipes()));
    }

    @Override
    public void switchToShoppingListView(FavoriteRecipeInputData favoriteRecipeInputData) {
        final String username = favoriteRecipeInputData.getUsername();
        final String[] recipeNames = favoriteRecipeInputData.getRecipeNames();
        System.out.println("Current logged in account: " + username);
        System.out.println("Current favoriteRecipe in account: " + Arrays.toString(recipeNames));

        final FavoriteRecipeOutputData favoriteRecipeOutputData = new FavoriteRecipeOutputData(
                inMemoryUserDataAccessObject.get(username).getName(),
                inMemoryUserDataAccessObject.get(username).getFavoriteRecipes());
        favoriteRecipePresenter.switchToShoppingListView(favoriteRecipeOutputData);
    }
}
