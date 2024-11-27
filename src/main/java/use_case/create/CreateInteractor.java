package use_case.create;

import data_access.RecipeDataAccessObject;
import entity.CommonRecipe;
import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;

import java.util.List;

/**
 * Interactor class for the Create Use Case.
 * Handles the business logic for creating a new recipe.
 */
public class CreateInteractor implements CreateInputBoundary {

    // The presenter that handles the output view logic
    private final CreateOutputBoundary createPresenter;

    // DAO for interacting with recipe data storage
    private final RecipeDataAccessObject recipeDataAccessObject;

    // Flag to indicate whether recipes have been loaded from the cloud
    private boolean recipesLoaded = false;

    // Factory for creating new Recipe objects
    private final RecipeFactory recipeFactory;

    /**
     * Constructor for CreateInteractor.
     *
     * @param createPresenter The output boundary (presenter) for the Create Use Case.
     * @param recipeFactory The factory used to create new Recipe objects.
     */
    public CreateInteractor(CreateOutputBoundary createPresenter, RecipeFactory recipeFactory) {
        this.createPresenter = createPresenter;
        this.recipeDataAccessObject = new RecipeDataAccessObject(); // Directly initializes a new DAO
        this.recipeFactory = recipeFactory;
    }

    /**
     * Executes the logic to create a new recipe.
     *
     * @param createInputData Input data for creating the recipe, including the dish name,
     *                        instructions, and ingredients.
     */
    public void execute(CreateInputData createInputData) {
        // Step 1: Check if recipes are already loaded from the cloud
        if (!recipesLoaded) {
            System.out.println("Loading recipes from cloud");
            recipeDataAccessObject.loadRecipesFromCloud(); // Load recipes from the cloud
            recipesLoaded = true; // Mark as loaded
        }

        // Step 2: Generate a unique ID for the new recipe
        final String id = recipeDataAccessObject.getMaxId() + 1;

        // Step 3: Set a default category for user-created recipes
        final String category = "created by user";

        // Step 4: Check if the recipe name already exists
        if (!recipeDataAccessObject.isNameInRecipes(createInputData.getDishname())) {
            // Step 5: Create a new recipe object using the factory
            final Recipe recipeCreated = recipeFactory.createRecipe(
                    id,
                    createInputData.getDishname(),
                    category,
                    createInputData.getInstruction(),
                    createInputData.getIngredient(),
                    0, // Initial like count
                    0  // Initial dislike count
            );

            // Step 6: Save the new recipe to the cache
            recipeDataAccessObject.saveRecipe(recipeCreated);

            // Step 7: Get the updated list of recipes and write it to the local JSON file
            final List<Recipe> updatedRecipe = recipeDataAccessObject.getCachedRecipes();
            recipeDataAccessObject.writeRecipesToFile(updatedRecipe);

            // Step 8: Synchronize the updated file with the cloud
            recipeDataAccessObject.deleteFileFromFileIo(); // Delete the old file from the cloud
            recipeDataAccessObject.uploadFileToFileIo();  // Upload the new file to the cloud

            // Step 9: Notify the presenter of success
            createPresenter.prepareSuccessView();
        } else {
            // If the recipe name already exists, notify the presenter of failure
            createPresenter.prepareFailureView();
        }
    }
}

