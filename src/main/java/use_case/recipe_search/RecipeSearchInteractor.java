package use_case.recipe_search;

import data_access.RecipeDataAccessObject;
import entity.CommonRecipe;

import java.util.List;

/**
 * The Recipe Search Interactor.
 */
public class RecipeSearchInteractor implements RecipeSearchInputBoundary {
    private final RecipeSearchOutputBoundary recipeSearchPresenter;
    private final RecipeDataAccessObject recipeDataAccessObject;
    private boolean recipesLoaded = false;  // Flag to ensure loading from cloud only once

    /**
     * Constructor for RecipeSearchInteractor.
     *
     * @param recipeSearchPresenter the output boundary (presenter) to display results
     */
    public RecipeSearchInteractor(RecipeSearchOutputBoundary recipeSearchPresenter) {
        this.recipeSearchPresenter = recipeSearchPresenter;
        this.recipeDataAccessObject = new RecipeDataAccessObject(); // Instantiate internally
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

        try {
            // Use cached recipes to search for the keyword
            List<CommonRecipe> recipes = recipeDataAccessObject.searchRecipes(searchKeyword);

            // Check if any recipes were found
            if (recipes.isEmpty()) {
                System.out.println("No recipes found for keyword: " + searchKeyword);
                recipeSearchPresenter.prepareFailureView("No recipes found for keyword: " + searchKeyword);
            } else {
                System.out.println("Recipes found: " + recipes.size());
                RecipeSearchOutputData recipeSearchOutputData = new RecipeSearchOutputData(searchKeyword, recipes);
                recipeSearchPresenter.prepareSuccessView(recipeSearchOutputData);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            recipeSearchPresenter.prepareFailureView("An error occurred while searching for recipes: " + e.getMessage());
        }
    }

    /**
     * Fetches all recipes from the API and stores them to the shared file for global access.
     * Should be called once to initialize the recipe storage.
     */
    public void initializeRecipeStorage() {
        System.out.println("Initializing shared recipe storage...");
        try {
            // Fetch all recipes (from 'a' to 'z') and cache them in RecipeDataAccessObject
            List<CommonRecipe> allRecipes = recipeDataAccessObject.fetchAllRecipes();
            System.out.println("Total recipes fetched: " + allRecipes.size());

            // Write all recipes to a shared JSON file and upload it
            recipeDataAccessObject.writeRecipesToFile(allRecipes);
            System.out.println("Shared recipe storage initialized successfully.");
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










