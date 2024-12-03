package use_case.favorite_recipe;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import interface_adapter.shopping_list.ShoppingListViewModel;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class favoriteInteractorTest {

    /**
     * A local implementation of FavoriteRecipeDataAccessInterface for testing purposes.
     */
    private static class LocalRecipeUserDataAccessObject implements FavoriteRecipeDataAccessInterface {
        private static Map<String, User> users = new HashMap<>();
        private String username;
        private String[] favoriteRecipes;

        private LocalRecipeUserDataAccessObject() {
            UserFactory userFactory = new CommonUserFactory();
            User user = userFactory.create("Test","Test");
            users.put("Test", user);
            username = "Test";
            favoriteRecipes = new String[]{null, null, null, null, null, null};
        }

        public static void save(CommonUser user) {
            users.put(user.getName(), user);
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public String[] getFavoriteRecipes() {
            return favoriteRecipes;
        }

        @Override
        public void setUsername(String username) {

        }

        @Override
        public void setFavoriteRecipes(String[] favoriteRecipes) {
            this.favoriteRecipes = favoriteRecipes;
        }

        @Override
        public void updateUserFavoriteRecipes(User user) {
            users.put(user.getName(), user);
        }

        @Override
        public User get(String username) {
            return users.get(username);
        }
    }

    @Test
    void execute() {
        // final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        final LocalRecipeUserDataAccessObject userDataAccessObject = new LocalRecipeUserDataAccessObject();
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
            public void switchToShoppingListView() {
            }
        };

        RecipeFactory recipeFactory = new CommonRecipeFactory();
        FavoriteRecipeInteractor interactor = new FavoriteRecipeInteractor(
                favoriteRecipePresenter,
                userDataAccessObject
        );
        inputData.setRecipeNames(inputData.getRecipeNames());

        interactor.execute(inputData);
        final FavoriteRecipeOutputData outputData = new FavoriteRecipeOutputData(
                userDataAccessObject.get(username).getName(),
                userDataAccessObject.get(username).getFavoriteRecipes());
        favoriteRecipePresenter.updateFavoriteRecipe(outputData);
        favoriteRecipePresenter.prepareFailureView("");
        favoriteRecipePresenter.switchToShoppingListView();

        String username1 = outputData.getUsername();
        String[] favoriteRecipe1 = outputData.getFavoriteRecipes();
        String username2 = userDataAccessObject.getUsername();
        String[] favoriteRecipe2 = userDataAccessObject.getFavoriteRecipes();
        userDataAccessObject.setUsername(username2);
        userDataAccessObject.setFavoriteRecipes(favoriteRecipe2);

        assertArrayEquals(new String[]{"The best Pizza in the world", "The Cake", "The Apple"}, userDataAccessObject.get(username).getFavoriteRecipes());
        // assertEquals(1, recipeRepository.getCachedRecipes().size(), "There should be exactly one recipe in the repository.");
        // assertEquals(recipeName, userRepository.getCreatedRecipes().get(0).getName(), "The recipe should be added to the user's created recipes.");
    }

    @Test
    void switchToShoppingListView() {

        // final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        final LocalRecipeUserDataAccessObject userDataAccessObject = new LocalRecipeUserDataAccessObject();

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
            public void switchToShoppingListView() {
            }
        };

        RecipeFactory recipeFactory = new CommonRecipeFactory();
        FavoriteRecipeInteractor interactor = new FavoriteRecipeInteractor(
                favoriteRecipePresenter,
                userDataAccessObject
        );


        final FavoriteRecipeOutputData outputData = new FavoriteRecipeOutputData(
                userDataAccessObject.get(username).getName(),
                userDataAccessObject.get(username).getFavoriteRecipes());
        favoriteRecipePresenter.updateFavoriteRecipe(outputData);
        favoriteRecipePresenter.prepareFailureView("");
        interactor.switchToShoppingListView();

        assertEquals("123", userDataAccessObject.get("123").getName());
        System.out.println(Arrays.toString(shoppingListViewModel.getState().getRecipeNames()));
        assertArrayEquals(null, shoppingListViewModel.getState().getRecipeNames());
    }
}