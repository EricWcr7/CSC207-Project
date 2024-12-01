package use_case.favorite;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import interface_adapter.shopping_list.ShoppingListState;
import interface_adapter.shopping_list.ShoppingListViewModel;
import org.junit.jupiter.api.Test;

import use_case.create_recipe.*;
import use_case.favorite_receipe.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class favoriteInteractorTest {

    @Test
    void execute() {
        final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

        String username = "123";

        String[] recipeNames = new String[] {"The best Pizza in the world", "The Cake", "The Apple"};

        CommonUser user = new CommonUser(username, " 123");

        userDataAccessObject.save(user);


        FavoriteRecipeInputData inputData = new FavoriteRecipeInputData(username, recipeNames);

        FavoriteRecipeOutputBoundary favoriteRecipePresenter = new FavoriteRecipeOutputBoundary() {
            @Override
            public void updateFavoriteRecipe(FavoriteRecipeOutputData outputData) {
            }

            @Override
            public void prepareFailureView(String errorMessage) {
            }

            @Override
            public void switchToShoppingListView(FavoriteRecipeOutputData outputData) {
            }
        };

        RecipeFactory recipeFactory = new CommonRecipeFactory();
        FavoriteRecipeInteractor interactor = new FavoriteRecipeInteractor(
                favoriteRecipePresenter,
                userDataAccessObject,
                userDataAccessObject
        );

        interactor.execute(inputData);
        final FavoriteRecipeOutputData outputData = new FavoriteRecipeOutputData(
                userDataAccessObject.get(username).getName(),
                userDataAccessObject.get(username).getFavoriteRecipes());
        favoriteRecipePresenter.updateFavoriteRecipe(outputData);
        favoriteRecipePresenter.prepareFailureView("");
        favoriteRecipePresenter.switchToShoppingListView(outputData);

        assertArrayEquals(new String[]{"The best Pizza in the world", "The Cake", "The Apple"}, userDataAccessObject.get(username).getFavoriteRecipes());
        // assertEquals(1, recipeRepository.getCachedRecipes().size(), "There should be exactly one recipe in the repository.");
        // assertEquals(recipeName, userRepository.getCreatedRecipes().get(0).getName(), "The recipe should be added to the user's created recipes.");
    }

    @Test
    void switchToShoppingListView() {

        final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

        final  ShoppingListViewModel shoppingListViewModel = new ShoppingListViewModel();

        String username = "123";

        String[] recipeNames = new String[] {"The best Pizza in the world", "The Cake", "The Apple"};

        CommonUser user = new CommonUser(username, " 123");

        userDataAccessObject.save(user);


        FavoriteRecipeInputData inputData = new FavoriteRecipeInputData(username, recipeNames);

        FavoriteRecipeOutputBoundary favoriteRecipePresenter = new FavoriteRecipeOutputBoundary() {
            @Override
            public void updateFavoriteRecipe(FavoriteRecipeOutputData outputData) {
            }

            @Override
            public void prepareFailureView(String errorMessage) {
            }

            @Override
            public void switchToShoppingListView(FavoriteRecipeOutputData outputData) {
                final ShoppingListState currentState = shoppingListViewModel.getState();
                currentState.setUsername(outputData.getUsername());
                currentState.setRecipeNames(outputData.getFavoriteRecipes());
                shoppingListViewModel.setState(currentState);
            }
        };

        RecipeFactory recipeFactory = new CommonRecipeFactory();
        FavoriteRecipeInteractor interactor = new FavoriteRecipeInteractor(
                favoriteRecipePresenter,
                userDataAccessObject,
                userDataAccessObject
        );


        final FavoriteRecipeOutputData outputData = new FavoriteRecipeOutputData(
                userDataAccessObject.get(username).getName(),
                userDataAccessObject.get(username).getFavoriteRecipes());
        favoriteRecipePresenter.updateFavoriteRecipe(outputData);
        favoriteRecipePresenter.prepareFailureView("");
        interactor.switchToShoppingListView(inputData);

        assertEquals("123", shoppingListViewModel.getState().getUsername());
        System.out.println(Arrays.toString(shoppingListViewModel.getState().getRecipeNames()));
        assertArrayEquals(new String[]{null, null, null, null, null, null}, shoppingListViewModel.getState().getRecipeNames());
    }
}