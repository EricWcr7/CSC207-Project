package use_case.recipe_search;

import entity.*;
import org.junit.jupiter.api.Test;
import use_case.favorite_receipe.FavoriteRecipeDataAccessInterface;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RecipeSearchInteractorTest {

    // Local in-memory implementation for Recipe Data Access
    private static class LocalRecipeSearchDataAccessObject implements RecipeSearchDataAccessInterface {
        private final Map<String, Recipe> recipes = new HashMap<>();
        private boolean fileExists = false;

        @Override
        public List<Recipe> fetchAllRecipes() {
            return new ArrayList<>(recipes.values());
        }

        @Override
        public List<Recipe> fetchRecipesByKeyword(String searchKeyword) {
            List<Recipe> result = new ArrayList<>();
            for (Recipe recipe : recipes.values()) {
                if (recipe.getName().toLowerCase().contains(searchKeyword.toLowerCase())) {
                    result.add(recipe);
                }
            }
            return result;
        }

        @Override
        public void writeRecipesToFile(List<Recipe> recipes) {
            fileExists = true; // Mock writing creates the file
        }

        @Override
        public void uploadFileToFileIo() {
            // Mock implementation; no action required
        }

        @Override
        public void loadRecipesFromCloud() {
            // Mock implementation; no action required
        }

        @Override
        public List<Recipe> searchRecipes(String searchKeyword) {
            return fetchRecipesByKeyword(searchKeyword);
        }

        @Override
        public String findFileOnFileIo(String fileName) {
            return fileExists ? "mockFileKey" : ""; // Return a mock file key if the file exists
        }

        public void addRecipe(Recipe recipe) {
            recipes.put(recipe.getName(), recipe);
        }
    }

    private static class LocalFavoriteRecipeDataAccessObject implements FavoriteRecipeDataAccessInterface {
        private String username;
        private String[] favoriteRecipes;
        private final Map<String, User> userMap = new HashMap<>();

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
            this.username = username;
        }

        @Override
        public void setFavoriteRecipes(String[] favoriteRecipes) {
            this.favoriteRecipes = favoriteRecipes;
        }

        @Override
        public void updateUserFavoriteRecipes(User user) {
            userMap.put(user.getName(), user);
        }

        @Override
        public User get(String username) {
            return userMap.get(username);
        }
    }

    @Test
    void initializeRecipeStorageFileExistsTest() {
        LocalRecipeSearchDataAccessObject recipeDao = new LocalRecipeSearchDataAccessObject();
        recipeDao.writeRecipesToFile(Collections.emptyList()); // Ensure file exists

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(recipeDao,
                null, null);

        assertDoesNotThrow(interactor::initializeRecipeStorage, "Should initialize without exception.");
    }

    @Test
    void initializeRecipeStorageFileNotExistsTest() {
        LocalRecipeSearchDataAccessObject recipeDao = new LocalRecipeSearchDataAccessObject();

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(recipeDao,
                null, null);

        assertDoesNotThrow(interactor::initializeRecipeStorage, "Should initialize without exception.");
        assertTrue(recipeDao.fileExists, "File should be created during initialization.");
    }

    @Test
    void switchToFavoriteRecipeViewTest() {
        // Mock data setup
        String username = "TestUser";
        String[] favoriteRecipes = {"Pasta", "Salad"};

        // Create a user using the UserFactory
        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create(username, "password123");
        user.setFavoriteRecipes(favoriteRecipes);

        // Mock favorite recipe data access
        LocalFavoriteRecipeDataAccessObject favoriteDao = new LocalFavoriteRecipeDataAccessObject();
        favoriteDao.setUsername(username);
        favoriteDao.setFavoriteRecipes(favoriteRecipes);
        favoriteDao.updateUserFavoriteRecipes(user);

        // Mock presenter
        RecipeSearchOutputBoundary presenter = new RecipeSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(RecipeSearchOutputData outputData) {
                fail("This method should not be called in this test.");
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("This method should not be called in this test.");
            }

            @Override
            public void switchToFavoriteRecipeView(RecipeSearchOutputData outputData) {
                assertEquals(username, outputData.getUsername());
                assertArrayEquals(favoriteRecipes, outputData.getFavoriteRecipes());
            }

            @Override
            public void switchToEditView() {
                fail("This method should not be called in this test.");
            }
        };

        // Test the interactor
        RecipeSearchInteractor interactor = new RecipeSearchInteractor(null,
                presenter, favoriteDao);
        RecipeSearchInputData inputData = new RecipeSearchInputData("", username, favoriteRecipes);

        interactor.switchToFavoriteRecipeView(inputData);
    }

    @Test
    void initializeRecipeStorage_FileExists_Test() {
        LocalRecipeSearchDataAccessObject recipeDao = new LocalRecipeSearchDataAccessObject();
        recipeDao.writeRecipesToFile(Collections.emptyList()); // Simulate file exists
        recipeDao.loadRecipesFromCloud(); // Simulate file loading

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(recipeDao,
                null, null);
        assertDoesNotThrow(interactor::initializeRecipeStorage, "File exists, should initialize without errors.");
    }

    @Test
    void initializeRecipeStorage_FileDoesNotExist_Test() {
        // Mock data access
        LocalRecipeSearchDataAccessObject recipeDao = new LocalRecipeSearchDataAccessObject() {
            private boolean recipesFetched = false;

            @Override
            public List<Recipe> fetchAllRecipes() {
                recipesFetched = true; // Simulate recipes being fetched
                return Collections.emptyList();
            }

            @Override
            public void writeRecipesToFile(List<Recipe> recipes) {
                assertTrue(recipesFetched, "Recipes should be fetched before writing to file.");
            }
        };

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(recipeDao,
                null, null);

        assertDoesNotThrow(() -> {
            interactor.initializeRecipeStorage();
        });
    }

    @Test
    void execute_NoRecipesFound_Test() {
        String searchKeyword = "Nonexistent";
        String username = "TestUser";
        String[] favoriteRecipes = {"Pasta", "Salad"};

        LocalRecipeSearchDataAccessObject recipeDao = new LocalRecipeSearchDataAccessObject();

        RecipeSearchOutputBoundary presenter = new RecipeSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(RecipeSearchOutputData outputData) {
                fail("This method should not be called.");
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                assertEquals("No recipes found for keyword: " + searchKeyword, errorMessage);
            }

            @Override
            public void switchToFavoriteRecipeView(RecipeSearchOutputData outputData) {
                fail("This method should not be called.");
            }

            @Override
            public void switchToEditView() {
                fail("This method should not be called.");
            }
        };

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(recipeDao,
                presenter, null);
        RecipeSearchInputData inputData = new RecipeSearchInputData(searchKeyword, username, favoriteRecipes);
        interactor.execute(inputData);
    }

    @Test
    void execute_RecipesFound_Test() {
        String searchKeyword = "Pasta";
        String username = "TestUser";
        String[] favoriteRecipes = {"Pasta", "Salad"};

        // Create input data and test its setters
        RecipeSearchInputData inputData = new RecipeSearchInputData(searchKeyword, username, favoriteRecipes);
        assertEquals(searchKeyword, inputData.getSearchKeyword());
        assertEquals(username, inputData.getUsername());
        assertArrayEquals(favoriteRecipes, inputData.getFavoriteRecipes());

        // Modify favorite recipes using setFavoriteRecipes
        String[] updatedFavoriteRecipes = {"Burger", "Fries"};
        inputData.setFavoriteRecipes(updatedFavoriteRecipes);
        assertArrayEquals(updatedFavoriteRecipes, inputData.getFavoriteRecipes());

        // Mock recipe DAO and add a matching recipe
        LocalRecipeSearchDataAccessObject recipeDao = new LocalRecipeSearchDataAccessObject();
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        Recipe recipe = recipeFactory.createRecipe(
                "1", "Pasta", "Main", "Cook pasta.",
                new HashMap<>(), 5, 2);
        recipeDao.addRecipe(recipe);

        // Mock presenter
        RecipeSearchOutputBoundary presenter = new RecipeSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(RecipeSearchOutputData outputData) {
                assertEquals(searchKeyword, outputData.getSearchKeyword());
                assertEquals(1, outputData.getRecipes().size());
                assertEquals("Pasta", outputData.getRecipes().get(0).getName());
                assertEquals(username, outputData.getUsername());
                assertArrayEquals(updatedFavoriteRecipes, outputData.getFavoriteRecipes());
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("This method should not be called.");
            }

            @Override
            public void switchToFavoriteRecipeView(RecipeSearchOutputData outputData) {
                fail("This method should not be called.");
            }

            @Override
            public void switchToEditView() {
                fail("This method should not be called.");
            }
        };

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(recipeDao,
                presenter, null);
        interactor.execute(inputData);
    }


    @Test
    void switchToEditView_Test() {
        RecipeSearchOutputBoundary presenter = new RecipeSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(RecipeSearchOutputData outputData) {
                fail("This method should not be called.");
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("This method should not be called.");
            }

            @Override
            public void switchToFavoriteRecipeView(RecipeSearchOutputData outputData) {
                fail("This method should not be called.");
            }

            @Override
            public void switchToEditView() {
                // This should be invoked, test passes if no errors
            }
        };

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(null,
                presenter, null);
        interactor.switchToEditView();
    }
}
