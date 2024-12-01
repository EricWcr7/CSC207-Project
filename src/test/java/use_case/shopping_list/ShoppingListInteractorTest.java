package use_case.shopping_list;

import entity.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListInteractorTest {

    // Local in-memory implementation for Recipe data access
    private static class LocalShoppingListRecipeDataAccessObject implements ShoppingListRecipeDataAccessInterface {
        private final Map<String, Recipe> cachedRecipes = new HashMap<>();

        @Override
        public Recipe getOneRecipe(String dishName) {
            return cachedRecipes.get(dishName);
        }

        public void addRecipe(Recipe recipe) {
            cachedRecipes.put(recipe.getName(), recipe);
        }
    }

    // Local in-memory implementation for User data access
    private static class LocalShoppingListUserDataAccessObject implements ShoppingListUserDataAccessInterface {
        private String currentUsername;
        private final Map<String, User> users = new HashMap<>();

        @Override
        public String getCurrentUsername() {
            return currentUsername;
        }

        @Override
        public String[] getFavoriteRecipes() {
            User currentUser = users.get(currentUsername);
            return currentUser != null ? currentUser.getFavoriteRecipes() : new String[0];
        }

        @Override
        public User get(String username) {
            return users.get(username);
        }

        public void addUser(User user) {
            users.put(user.getName(), user);
        }

        public void setCurrentUsername(String username) {
            this.currentUsername = username;
        }
    }

    @Test
    void successTest() {
        // Input data setup
        String username = "Alice";
        String[] recipeNames = {"Pasta", "Salad"};

        // Create RecipeFactory and recipes
        RecipeFactory recipeFactory = new CommonRecipeFactory();

        Map<String, String> pastaIngredients = new HashMap<>();
        pastaIngredients.put("Flour", "2 cups");
        pastaIngredients.put("Tomato Sauce", "1 cup");

        Map<String, String> saladIngredients = new HashMap<>();
        saladIngredients.put("Lettuce", "1 head");
        saladIngredients.put("Tomato", "2");

        Recipe pastaRecipe = recipeFactory.createRecipe(
                "1", "Pasta", "Main", "Boil pasta and add sauce.", pastaIngredients, 10, 2);
        Recipe saladRecipe = recipeFactory.createRecipe(
                "2", "Salad", "Appetizer", "Chop vegetables and mix.", saladIngredients, 5, 1);

        // Local DAO setup
        LocalShoppingListRecipeDataAccessObject recipeDao = new LocalShoppingListRecipeDataAccessObject();
        recipeDao.addRecipe(pastaRecipe);
        recipeDao.addRecipe(saladRecipe);

        // Create a user using the UserFactory
        UserFactory userFactory = new CommonUserFactory();
        User alice = userFactory.create(username, "password123");
        alice.setFavoriteRecipes(recipeNames);

        LocalShoppingListUserDataAccessObject userDao = new LocalShoppingListUserDataAccessObject();
        userDao.addUser(alice);
        userDao.setCurrentUsername(username);

        // Create a presenter to validate the output
        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(recipeNames, shoppingListOutputData.getRecipeNames());

                Set<String> expectedIngredients = new HashSet<>(Arrays.asList("Flour", "Tomato Sauce", "Lettuce", "Tomato"));
                assertEquals(expectedIngredients, shoppingListOutputData.getIngredients());
            }
        };

        // Execute the interactor
        ShoppingListInputBoundary interactor = new ShoppingListInteractor(successPresenter, recipeDao, userDao);
        interactor.execute();
    }

    @Test
    void capitalizeFirstLetterTest() throws Exception {
        ShoppingListInteractor interactor = new ShoppingListInteractor(null, null, null);
        Method capitalizeMethod = ShoppingListInteractor.class.getDeclaredMethod("capitalizeFirstLetter", String.class);
        capitalizeMethod.setAccessible(true);

        assertEquals("Flour", capitalizeMethod.invoke(interactor, "flour"));
        assertEquals("Tomato Sauce", capitalizeMethod.invoke(interactor, "tomato sauce"));
        assertEquals("Lettuce", capitalizeMethod.invoke(interactor, "lettuce"));
        assertEquals("", capitalizeMethod.invoke(interactor, ""));
        assertNull(capitalizeMethod.invoke(interactor, (Object) null));
    }

    @Test
    void emptyRecipeListTest() {
        // Input data setup
        String username = "Alice";

        UserFactory userFactory = new CommonUserFactory();
        User alice = userFactory.create(username, "password123");
        alice.setFavoriteRecipes(new String[0]);

        LocalShoppingListUserDataAccessObject userDao = new LocalShoppingListUserDataAccessObject();
        userDao.addUser(alice);
        userDao.setCurrentUsername(username);

        LocalShoppingListRecipeDataAccessObject recipeDao = new LocalShoppingListRecipeDataAccessObject();

        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(new String[0], shoppingListOutputData.getRecipeNames());
                assertTrue(shoppingListOutputData.getIngredients().isEmpty());
            }
        };

        ShoppingListInputBoundary interactor = new ShoppingListInteractor(successPresenter, recipeDao, userDao);
        interactor.execute();
    }

    @Test
    void nullRecipeNameTest() {
        // Setup
        String username = "Bob";
        String[] recipeNames = {null, "null", "MissingRecipe"};

        UserFactory userFactory = new CommonUserFactory();
        User bob = userFactory.create(username, "password123");
        bob.setFavoriteRecipes(recipeNames);

        LocalShoppingListUserDataAccessObject userDao = new LocalShoppingListUserDataAccessObject();
        userDao.addUser(bob);
        userDao.setCurrentUsername(username);

        LocalShoppingListRecipeDataAccessObject recipeDao = new LocalShoppingListRecipeDataAccessObject();

        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(new String[]{"MissingRecipe"}, shoppingListOutputData.getRecipeNames());
                assertTrue(shoppingListOutputData.getIngredients().isEmpty());
            }
        };

        ShoppingListInputBoundary interactor = new ShoppingListInteractor(successPresenter, recipeDao, userDao);
        interactor.execute();
    }

    @Test
    void missingRecipeTest() {
        // Setup
        String username = "Charlie";
        String[] recipeNames = {"UnknownRecipe"};

        UserFactory userFactory = new CommonUserFactory();
        User charlie = userFactory.create(username, "password456");
        charlie.setFavoriteRecipes(recipeNames);

        LocalShoppingListUserDataAccessObject userDao = new LocalShoppingListUserDataAccessObject();
        userDao.addUser(charlie);
        userDao.setCurrentUsername(username);

        LocalShoppingListRecipeDataAccessObject recipeDao = new LocalShoppingListRecipeDataAccessObject();

        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(recipeNames, shoppingListOutputData.getRecipeNames());
                assertTrue(shoppingListOutputData.getIngredients().isEmpty());
            }
        };

        ShoppingListInputBoundary interactor = new ShoppingListInteractor(successPresenter, recipeDao, userDao);
        interactor.execute();
    }

    @Test
    void emptyAndWhitespaceIngredientsTest() {
        // Setup
        String username = "Dana";
        String[] recipeNames = {"WhitespaceIngredients", "EmptyIngredients"};

        RecipeFactory recipeFactory = new CommonRecipeFactory();

        Recipe whitespaceRecipe = recipeFactory.createRecipe(
                "1", "WhitespaceIngredients", "Main", " ", new HashMap<>(), 5, 1);
        Recipe emptyRecipe = recipeFactory.createRecipe(
                "2", "EmptyIngredients", "Appetizer", "", new HashMap<>(), 5, 1);

        LocalShoppingListRecipeDataAccessObject recipeDao = new LocalShoppingListRecipeDataAccessObject();
        recipeDao.addRecipe(whitespaceRecipe);
        recipeDao.addRecipe(emptyRecipe);

        UserFactory userFactory = new CommonUserFactory();
        User dana = userFactory.create(username, "password789");
        dana.setFavoriteRecipes(recipeNames);

        LocalShoppingListUserDataAccessObject userDao = new LocalShoppingListUserDataAccessObject();
        userDao.addUser(dana);
        userDao.setCurrentUsername(username);

        ShoppingListOutputBoundary successPresenter = new ShoppingListOutputBoundary() {
            @Override
            public void presentShoppingList(ShoppingListOutputData shoppingListOutputData) {
                assertEquals(username, shoppingListOutputData.getUsername());
                assertArrayEquals(recipeNames, shoppingListOutputData.getRecipeNames());
                assertTrue(shoppingListOutputData.getIngredients().isEmpty());
            }
        };

        ShoppingListInputBoundary interactor = new ShoppingListInteractor(successPresenter, recipeDao, userDao);
        interactor.execute();
    }

}
