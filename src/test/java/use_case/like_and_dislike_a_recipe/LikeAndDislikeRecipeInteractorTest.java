package use_case.like_and_dislike_a_recipe;

import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import org.junit.jupiter.api.Test;
import use_case.like_and_dislike_a_recipe.dislike.DislikeRecipeInteractor;
import use_case.like_and_dislike_a_recipe.dislike.DislikeRecipeOutputBoundary;
import use_case.like_and_dislike_a_recipe.dislike.DislikeRecipeOutputData;
import use_case.like_and_dislike_a_recipe.like.LikeRecipeInteractor;
import use_case.like_and_dislike_a_recipe.like.LikeRecipeOutputBoundary;
import use_case.like_and_dislike_a_recipe.like.LikeRecipeOutputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LikeAndDislikeRecipeInteractorTest {

    /**
     * A local implementation of LikeAndDislikeRecipeDataAccessInterface for testing purposes.
     */
    private static class LocalRecipeDataAccessObject implements LikeAndDislikeRecipeDataAccessInterface {
        private final List<Recipe> recipes = new ArrayList<>();

        public void addRecipe(Recipe recipe) {
            recipes.add(recipe);
        }

        @Override
        public Recipe getOneRecipe(String name) {
            return recipes.stream()
                    .filter(recipe -> recipe.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public List<Recipe> getCachedRecipes() {
            return new ArrayList<>(recipes);
        }

        @Override
        public void writeRecipesToFile(List<Recipe> updatedRecipes) {
            // Simulate writing to a file
        }

        @Override
        public void deleteFileFromFileIo() {
            // Simulate deleting a file
        }

        @Override
        public void uploadFileToFileIo() {
            // Simulate uploading a file
        }

        @Override
        public void updateChangedRecipes(List<Recipe> updatedRecipes) {

        }

    }

    /**
     * A local implementation of UserLikeAndDislikeDataAccessInterface for testing purposes.
     */
    private static class LocalUserDataAccessObject implements UserLikeAndDislikeDataAccessInterface {
        private final List<String> likedRecipes = new ArrayList<>();
        private final List<String> dislikedRecipes = new ArrayList<>();

        @Override
        public String getCurrentUsername() {
            return "Lana Del Rey";
        }

        @Override
        public boolean hasUserLikedRecipe(String recipeName) {
            return likedRecipes.contains(recipeName);
        }

        @Override
        public void addLikedRecipe(String recipeName) {
            likedRecipes.add(recipeName);
        }

        @Override
        public boolean hasUserDislikedRecipe(String recipeName) {
            return dislikedRecipes.contains(recipeName);
        }

        @Override
        public void addDislikedRecipe(String recipeName) {
            dislikedRecipes.add(recipeName);
        }

        @Override
        public void updateUserLikedRecipe(String recipeName) {
            // Simulate updating to a file
        }

        @Override
        public void updateUserDislikedRecipe(String recipeName) {
            // Simulate updating to a file
        }
    }

    @Test
    void likeSuccessTest() {
        // Input data
        String username = "Lana Del Rey";
        String[] recipeNames = {"Pasta", "Salad"};
        String recipeName = "Salad";
        LikeAndDislikeRecipeInputData inputData = new LikeAndDislikeRecipeInputData(recipeName);

        // Create RecipeFactory and recipes
        RecipeFactory recipeFactory = new CommonRecipeFactory();

        Map<String, String> saladIngredients = new HashMap<>();
        saladIngredients.put("Tomato Sauce", "2 tablespoons"); // Overlapping ingredient
        saladIngredients.put("Lettuce", "1 head");
        saladIngredients.put("Cucumber", "1 medium");
        saladIngredients.put("Olive Oil", "2 tablespoons");
        saladIngredients.put("Lemon Juice", "1 tablespoon");
        saladIngredients.put("Salt", "to taste");
        Recipe saladRecipe = recipeFactory.createRecipe("1", "Salad", "Side",
                "Lettuce: 1 head\nCucumber: 1 medium\nTomato Sauce: 2 tablespoons\nOlive Oil: 2 tablespoons\nLemon Juice: 1 tablespoon\nSalt: to taste",
                saladIngredients, 5, 4);


        LocalRecipeDataAccessObject recipeRepository = new LocalRecipeDataAccessObject();
        recipeRepository.addRecipe(saladRecipe);
        LocalUserDataAccessObject userRepository = new LocalUserDataAccessObject();


        LikeRecipeOutputBoundary presenter = new LikeRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(LikeRecipeOutputData outputData) {
                assertEquals("Salad", outputData.getRecipeName(), "Recipe name should match.");
                assertEquals(6, outputData.getLikeNumber(), "Like count should be incremented.");
                assertEquals(userRepository.getCurrentUsername(), username);
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Like operation should not fail.");
            }
        };

        LikeRecipeInteractor interactor = new LikeRecipeInteractor(
                recipeRepository,
                userRepository,
                presenter
        );

        interactor.execute(inputData);
        userRepository.addLikedRecipe(recipeName);

        assertTrue(userRepository.hasUserLikedRecipe("Salad"), "The user should have liked the recipe.");
        assertEquals(6, saladRecipe.getLikeNumber(), "Recipe's like number should be incremented.");
    }

    @Test
    void failureAlreadyLikedTest() {
        // Input data
        String username = "Lana Del Rey";
        String[] recipeNames = {"Pasta", "Salad"};
        String recipeName = "Salad";
        LikeAndDislikeRecipeInputData inputData = new LikeAndDislikeRecipeInputData(recipeName);

        // Create RecipeFactory and recipes
        RecipeFactory recipeFactory = new CommonRecipeFactory();

        Map<String, String> saladIngredients = new HashMap<>();
        saladIngredients.put("Tomato Sauce", "2 tablespoons"); // Overlapping ingredient
        saladIngredients.put("Lettuce", "1 head");
        saladIngredients.put("Cucumber", "1 medium");
        saladIngredients.put("Olive Oil", "2 tablespoons");
        saladIngredients.put("Lemon Juice", "1 tablespoon");
        saladIngredients.put("Salt", "to taste");
        Recipe saladRecipe = recipeFactory.createRecipe("1", "Salad", "Side",
                "Lettuce: 1 head\nCucumber: 1 medium\nTomato Sauce: 2 tablespoons\nOlive Oil: 2 tablespoons\nLemon Juice: 1 tablespoon\nSalt: to taste",
                saladIngredients, 5, 4);

        LocalRecipeDataAccessObject recipeRepository = new LocalRecipeDataAccessObject();
        recipeRepository.addRecipe(saladRecipe);

        // Pretend user who already liked this recipe
        LocalUserDataAccessObject userRepository = new LocalUserDataAccessObject();
        userRepository.addLikedRecipe(recipeName);
        saladRecipe.incrementLikeNumber();


        LikeRecipeOutputBoundary presenter = new LikeRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(LikeRecipeOutputData outputData) {
                fail("Like operation should not succeed for already liked recipe.");
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                assertEquals("You have already liked this recipe", errorMessage, "Error message should indicate recipe was already liked.");
            }
        };

        LikeRecipeInteractor interactor = new LikeRecipeInteractor(
                recipeRepository,
                userRepository,
                presenter
        );

        interactor.execute(inputData);

        assertEquals(6, saladRecipe.getLikeNumber(), "Recipe's like number should not change.");
    }

    @Test
    void dislikeSuccessTest() {
        // Input data
        String username = "Lana Del Rey";
        String[] recipeNames = {"Pasta", "Salad"};
        String recipeName = "Salad";
        LikeAndDislikeRecipeInputData inputData = new LikeAndDislikeRecipeInputData(recipeName);

        // Create RecipeFactory and recipes
        RecipeFactory recipeFactory = new CommonRecipeFactory();

        Map<String, String> saladIngredients = new HashMap<>();
        saladIngredients.put("Tomato Sauce", "2 tablespoons"); // Overlapping ingredient
        saladIngredients.put("Lettuce", "1 head");
        saladIngredients.put("Cucumber", "1 medium");
        saladIngredients.put("Olive Oil", "2 tablespoons");
        saladIngredients.put("Lemon Juice", "1 tablespoon");
        saladIngredients.put("Salt", "to taste");
        Recipe saladRecipe = recipeFactory.createRecipe("1", "Salad", "Side",
                "Lettuce: 1 head\nCucumber: 1 medium\nTomato Sauce: 2 tablespoons\nOlive Oil: 2 tablespoons\nLemon Juice: 1 tablespoon\nSalt: to taste",
                saladIngredients, 5, 4);


        LocalRecipeDataAccessObject recipeRepository = new LocalRecipeDataAccessObject();
        recipeRepository.addRecipe(saladRecipe);
        LocalUserDataAccessObject userRepository = new LocalUserDataAccessObject();


        DislikeRecipeOutputBoundary presenter = new DislikeRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(DislikeRecipeOutputData outputData) {
                assertEquals("Salad", outputData.getRecipeName(), "Recipe name should match.");
                assertEquals(5, outputData.getDislikeNumber(), "Dislike count should be incremented.");
                assertEquals(userRepository.getCurrentUsername(), username);
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Dislike operation should not fail.");
            }
        };

        DislikeRecipeInteractor interactor = new DislikeRecipeInteractor(
                recipeRepository,
                userRepository,
                presenter
        );

        interactor.execute(inputData);
        userRepository.addDislikedRecipe(recipeName);

        assertTrue(userRepository.hasUserDislikedRecipe("Salad"), "The user should have disliked the recipe.");
        assertEquals(5, saladRecipe.getDislikeNumber(), "Recipe's dislike number should be incremented.");
    }

    @Test
    void failureAlreadyDislikedTest() {
        // Input data
        String username = "Lana Del Rey";
        String[] recipeNames = {"Pasta", "Salad"};
        String recipeName = "Salad";
        LikeAndDislikeRecipeInputData inputData = new LikeAndDislikeRecipeInputData(recipeName);

        // Create RecipeFactory and recipes
        RecipeFactory recipeFactory = new CommonRecipeFactory();

        Map<String, String> saladIngredients = new HashMap<>();
        saladIngredients.put("Tomato Sauce", "2 tablespoons"); // Overlapping ingredient
        saladIngredients.put("Lettuce", "1 head");
        saladIngredients.put("Cucumber", "1 medium");
        saladIngredients.put("Olive Oil", "2 tablespoons");
        saladIngredients.put("Lemon Juice", "1 tablespoon");
        saladIngredients.put("Salt", "to taste");
        Recipe saladRecipe = recipeFactory.createRecipe("1", "Salad", "Side",
                "Lettuce: 1 head\nCucumber: 1 medium\nTomato Sauce: 2 tablespoons\nOlive Oil: 2 tablespoons\nLemon Juice: 1 tablespoon\nSalt: to taste",
                saladIngredients, 5, 4);

        LocalRecipeDataAccessObject recipeRepository = new LocalRecipeDataAccessObject();
        recipeRepository.addRecipe(saladRecipe);

        // Pretend user who already liked this recipe
        LocalUserDataAccessObject userRepository = new LocalUserDataAccessObject();
        userRepository.addDislikedRecipe(recipeName);
        saladRecipe.incrementDislikeNumber();


        DislikeRecipeOutputBoundary presenter = new DislikeRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(DislikeRecipeOutputData outputData) {
                fail("Dislike operation should not succeed for already liked recipe.");
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                assertEquals("You have already disliked this recipe", errorMessage, "Error message should indicate recipe was already liked.");
            }
        };

        DislikeRecipeInteractor interactor = new DislikeRecipeInteractor(
                recipeRepository,
                userRepository,
                presenter
        );

        interactor.execute(inputData);

        assertEquals(5, saladRecipe.getDislikeNumber(), "Recipe's like number should not change.");
    }

}

