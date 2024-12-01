package use_case.favorite_receipe;

import java.util.Arrays;

import entity.User;
import use_case.shopping_list.ShoppingListUserDataAccessInterface;

/**
 * The FavoriteRecipe Interactor.
 */
public class FavoriteRecipeInteractor implements FavoriteRecipeInputBoundary {
    private final FavoriteRecipeOutputBoundary favoriteRecipePresenter;
    private final FavoriteRecipeDataAccessInterface userDataAccessObject;

    public FavoriteRecipeInteractor(FavoriteRecipeOutputBoundary favoriteRecipePresenter,
                                    FavoriteRecipeDataAccessInterface userDataAccessObject) {
        this.favoriteRecipePresenter = favoriteRecipePresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(FavoriteRecipeInputData favoriteRecipeInputData) {
        final String username = favoriteRecipeInputData.getUsername();
        final String[] favoriteRecipes = favoriteRecipeInputData.getRecipeNames();
        final User user = userDataAccessObject.get(username);
        user.setFavoriteRecipes(favoriteRecipes);
        userDataAccessObject.updateUserFavoriteRecipes(user);
        System.out.println("Current account in InMemoryUserDataAccessObject: "
                + userDataAccessObject.get(username).getName());
        System.out.println("Current favoriteRecipes in InMemoryUserDataAccessObject: "
                + Arrays.toString(userDataAccessObject.get(username).getFavoriteRecipes()));
        final FavoriteRecipeOutputData favoriteRecipeOutputData = new FavoriteRecipeOutputData(
                userDataAccessObject.get(username).getName(),
                userDataAccessObject.get(username).getFavoriteRecipes());
        favoriteRecipePresenter.updateFavoriteRecipe(favoriteRecipeOutputData);
    }

    @Override
    public void switchToShoppingListView() {
        favoriteRecipePresenter.switchToShoppingListView();
    }
}
