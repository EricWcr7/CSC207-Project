package use_case.create_recipe;

import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateInteractorTest {

    /**
     * A local implementation of CreateDataAccessInterface for testing purposes.
     */
    private static class LocalRecipeRecipeDataAccessObject implements CreateRecipeDataAccessInterface {
        private final List<Recipe> recipes = new ArrayList<>();
        private String maxId = "0";

        @Override
        public void saveRecipe(Recipe recipe) {
            recipes.add(recipe);
            maxId = Integer.toString(Math.max(Integer.parseInt(maxId), Integer.parseInt(recipe.getId()))); // Update maxId
        }

        @Override
        public boolean isNameInRecipes(String name) {
            return recipes.stream().anyMatch(recipe -> recipe.getName().equals(name));
        }

        @Override
        public String getMaxId() {
            return maxId;
        }

        @Override
        public void loadRecipesFromCloud() {
        }

        @Override
        public List<Recipe> getCachedRecipes() {
            return new ArrayList<>(recipes);
        }

        @Override
        public void writeRecipesToFile(List<Recipe> updatedRecipes) {
        }

        @Override
        public void deleteFileFromFileIo() {
        }

        @Override
        public void uploadFileToFileIo() {
        }
    }


    /**
     * A local implementation of CreateUserDataAccessInterface for testing purposes.
     */
    private static class LocalRecipeUserDataAccessObject implements CreateRecipeUserDataAccessInterface {
        private final List<Recipe> createdRecipes = new ArrayList<>();

        @Override
        public void addCreatedRecipe(Recipe recipe) {
            createdRecipes.add(recipe);
        }

        public List<Recipe> getCreatedRecipes() {
            return new ArrayList<>(createdRecipes);
        }
    }

    @Test
    void successTest() {

        HashMap<String, String> ingredients = new HashMap<>();
        ingredients.put("Cornmeal", "2 tablespoons");
        ingredients.put("Flour", "2-3 tablespoons");
        ingredients.put("Pizza dough", "1 lb");
        ingredients.put("Marinara sauce", "3/4 cup");
        ingredients.put("Mozzarella cheese", "3 oz");
        ingredients.put("Parmesan cheese", "2 tablespoons");
        ingredients.put("Basil", "1 teaspoon");

        String recipeName = "The best Pizza in the world";
        String instructions = "Place a pizza stone in the oven and heat to 500ºF. " +
                "Dust a baking sheet with cornmeal.";

        CreateRecipeInputData inputData = new CreateRecipeInputData(recipeName, instructions, ingredients);

        LocalRecipeRecipeDataAccessObject recipeRepository = new LocalRecipeRecipeDataAccessObject();
        LocalRecipeUserDataAccessObject userRepository = new LocalRecipeUserDataAccessObject();

        CreateRecipeOutputBoundary createPresenter = new CreateRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertTrue(true, "Recipe creation succeeded.");
            }

            @Override
            public void prepareFailureView() {
                fail("Recipe creation failed unexpectedly.");
            }
        };

        RecipeFactory recipeFactory = new CommonRecipeFactory();
        CreateRecipeInteractor interactor = new CreateRecipeInteractor(
                createPresenter,
                recipeFactory,
                recipeRepository,
                userRepository
        );

        interactor.execute(inputData);

        assertTrue(recipeRepository.isNameInRecipes(recipeName), "The recipe should now exist in the repository.");
        assertEquals(1, recipeRepository.getCachedRecipes().size(), "There should be exactly one recipe in the repository.");
        assertEquals(recipeName, userRepository.getCreatedRecipes().get(0).getName(), "The recipe should be added to the user's created recipes.");
    }

    @Test
    void failureRecipeExistsTest() {

        HashMap<String, String> ingredients = new HashMap<>();
        ingredients.put("Cornmeal", "2 tablespoons");
        String recipeName = "Pizza Express Margherita";
        String instructions = "Existing recipe instructions.";

        LocalRecipeRecipeDataAccessObject recipeRepository = new LocalRecipeRecipeDataAccessObject();
        LocalRecipeUserDataAccessObject userRepository = new LocalRecipeUserDataAccessObject();

        RecipeFactory recipeFactory = new CommonRecipeFactory();
        Recipe existingRecipe = recipeFactory.createRecipe(
                "1",
                recipeName,
                "existing category",
                instructions,
                ingredients,
                0,
                0
        );
        recipeRepository.saveRecipe(existingRecipe);

        CreateRecipeOutputBoundary failurePresenter = new CreateRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                fail("Recipe creation succeeded unexpectedly.");
            }

            @Override
            public void prepareFailureView() {
                assertTrue(true, "Duplicate recipe creation was correctly prevented.");
            }
        };

        CreateRecipeInteractor interactor = new CreateRecipeInteractor(
                failurePresenter,
                recipeFactory,
                recipeRepository,
                userRepository
        );

        CreateRecipeInputData inputData = new CreateRecipeInputData(recipeName, "Some new instructions", ingredients);
        interactor.execute(inputData);

        List<Recipe> recipes = recipeRepository.getCachedRecipes();
        assertEquals(1, recipes.size(), "Only one recipe should exist.");
        assertEquals(recipeName, recipes.get(0).getName(), "The existing recipe name should remain unchanged.");
    }


}
