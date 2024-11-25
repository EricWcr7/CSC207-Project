package use_case.recipe_search;

import entity.Recipe;

import java.util.Arrays;
import java.util.List;

/**
 * The Recipe Search Interactor.
 */
public class RecipeSearchInteractor implements RecipeSearchInputBoundary {
    private final RecipeSearchOutputBoundary recipeSearchPresenter;
    private final RecipeSearchDataAccessInterface recipeDataAccessObject;
    private boolean recipesLoaded = false;  // Flag to ensure loading from cloud only once

    /**
     * Constructor for RecipeSearchInteractor.
     *
     * @param recipeDataAccessObject the Data Access interface of recipe search
     * @param recipeSearchPresenter the output boundary (presenter) to display results
     */
    public RecipeSearchInteractor(RecipeSearchDataAccessInterface recipeDataAccessObject,
                                  RecipeSearchOutputBoundary recipeSearchPresenter) {
        this.recipeSearchPresenter = recipeSearchPresenter;
        this.recipeDataAccessObject = recipeDataAccessObject; // Instantiate internally
    }

    /**
     * Executes the search use case based on a search keyword.
     *
     * @param recipeSearchInputData the input data containing the search keyword
     */
    @Override
    public void execute(RecipeSearchInputData recipeSearchInputData) {
        // Ensure recipes are loaded from the cloud only once
        if (!recipesLoaded) {
            System.out.println("Loading recipes from cloud for the first time...");
            recipeDataAccessObject.loadRecipesFromCloud();
            recipesLoaded = true;
        }

        final String searchKeyword = recipeSearchInputData.getSearchKeyword();
        System.out.println("Interactor received search keyword: " + searchKeyword);
        final String username = recipeSearchInputData.getUsername();
        final String[] favoriteRecipes = recipeSearchInputData.getFavoriteRecipes();
        System.out.println("Current logged in account: " + username);
        System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));

        try {
            // Use cached recipes to search for the keyword
            List<Recipe> recipes = recipeDataAccessObject.searchRecipes(searchKeyword);

            // Check if any recipes were found
            if (recipes.isEmpty()) {
                System.out.println("No recipes found for keyword: " + searchKeyword);
                recipeSearchPresenter.prepareFailureView("No recipes found for keyword: " + searchKeyword);
            } else {
                System.out.println("Recipes found: " + recipes.size());
                RecipeSearchOutputData recipeSearchOutputData = new RecipeSearchOutputData(
                        searchKeyword, recipes, username, favoriteRecipes);
                recipeSearchPresenter.prepareSuccessView(recipeSearchOutputData);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            recipeSearchPresenter.prepareFailureView("An error occurred while searching for recipes: " + e.getMessage());
        }
    }

    @Override
    public void initializeRecipeStorage() {
        System.out.println("Initializing shared recipe storage...");
        try {
            // Step 1: Check if "all_recipes.json" exists on File.io using the DAO
            String fileKey = recipeDataAccessObject.findFileOnFileIo("all_recipes.json");
            System.out.println(fileKey);
            if (fileKey != "") {
                // Case 1: If the file exists, load it from File.io using the DAO
                System.out.println("File 'all_recipes.json' found on File.io with ID: " + fileKey);
                recipeDataAccessObject.loadRecipesFromCloud(); // Load recipes from the existing JSON file
                System.out.println("Recipes loaded from 'all_recipes.json' successfully.");
            }
            else {
                // Case 2: If the file does not exist, fetch all recipes from the API using the DAO
                List<Recipe> allRecipes = recipeDataAccessObject.fetchAllRecipes();
                System.out.println("Total recipes fetched: " + allRecipes.size());
                // Write all recipes to a shared JSON file and upload it
                recipeDataAccessObject.writeRecipesToFile(allRecipes);
                System.out.println("Shared recipe storage initialized successfully.");
                System.out.println("Loading recipes from File.io...");
                recipeDataAccessObject.loadRecipesFromCloud();
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize recipe storage: " + e.getMessage());
        }
    }

    @Override
    public void switchToFavoriteRecipeView() {
        recipeSearchPresenter.switchToFavoriteRecipeView();
    }

    @Override
    public void switchToEditView() {
        recipeSearchPresenter.switchToEditView();
    }
}









