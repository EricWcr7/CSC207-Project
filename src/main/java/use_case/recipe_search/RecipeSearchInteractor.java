package use_case.recipe_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.Recipe;
import use_case.favorite_recipe.FavoriteRecipeDataAccessInterface;

/**
 * The Recipe Search Interactor.
 */
public class RecipeSearchInteractor implements RecipeSearchInputBoundary {
    private final RecipeSearchOutputBoundary recipeSearchPresenter;
    private final RecipeSearchDataAccessInterface recipeDataAccessObject;
    private final FavoriteRecipeDataAccessInterface favoriteRecipeDataAccessObject;
    private boolean recipesLoaded;
    // Flag to ensure loading from cloud only once

    /**
     * Constructor for RecipeSearchInteractor.
     *
     * @param recipeDataAccessObject the Data Access interface of recipe search
     * @param recipeSearchPresenter the output boundary (presenter) to display results
     * @param favoriteRecipeDataAccessObject the FavoriteRecipeDataAccessInterface
     */
    public RecipeSearchInteractor(RecipeSearchDataAccessInterface recipeDataAccessObject,
                                  RecipeSearchOutputBoundary recipeSearchPresenter,
                                  FavoriteRecipeDataAccessInterface favoriteRecipeDataAccessObject) {
        this.recipeSearchPresenter = recipeSearchPresenter;
        this.recipeDataAccessObject = recipeDataAccessObject;
        // Instantiate internally
        this.favoriteRecipeDataAccessObject = favoriteRecipeDataAccessObject;
        recipesLoaded = false;
    }

    /**
     * Executes the search use case based on a search keyword.
     *
     * @param recipeSearchInputData the input data containing the search keyword
     */
    @Override
    public void execute(RecipeSearchInputData recipeSearchInputData) {
        System.out.println("Loading recipes from cloud for the first time...");
        recipeDataAccessObject.findFileOnFileIo("all_recipes.json");
        recipeDataAccessObject.loadRecipesFromCloud();
        recipesLoaded = true;
        final String searchKeyword = recipeSearchInputData.getSearchKeyword();
        System.out.println("Interactor received search keyword: " + searchKeyword);
        final String username = recipeSearchInputData.getUsername();
        final String[] favoriteRecipes = recipeSearchInputData.getFavoriteRecipes();
        System.out.println("Current logged in account: " + username);
        System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));

        final List<Recipe> recipes = recipeDataAccessObject.searchRecipes(searchKeyword);

        // Check if any recipes were found
        if (recipes.isEmpty()) {
            System.out.println("No recipes found for keyword: " + searchKeyword);
            recipeSearchPresenter.prepareFailureView("No recipes found for keyword: " + searchKeyword);
        }
        else {
            System.out.println("Recipes found: " + recipes.size());
            final RecipeSearchOutputData recipeSearchOutputData = new RecipeSearchOutputData(
                    searchKeyword, recipes, username, favoriteRecipes);
            recipeSearchPresenter.prepareSuccessView(recipeSearchOutputData);
        }
    }

    @Override
    public void initializeRecipeStorage() {
        System.out.println("Initializing shared recipe storage...");
        final String fileKey = recipeDataAccessObject.findFileOnFileIo("all_recipes.json");
        System.out.println(fileKey);
        if (!fileKey.isEmpty()) {
            // Case 1: If the file exists, load it from File.io using the DAO
            System.out.println("File 'all_recipes.json' found on File.io with ID: " + fileKey);
            recipeDataAccessObject.loadRecipesFromCloud();
            // Load recipes from the existing JSON file
            System.out.println("Recipes loaded from 'all_recipes.json' successfully.");
        }
        else {
            // Case 2: If the file does not exist, fetch all recipes from the API using the DAO
            final List<Recipe> allRecipes = recipeDataAccessObject.fetchAllRecipes();
            System.out.println("Total recipes fetched: " + allRecipes.size());
            // Write all recipes to a shared JSON file and upload it
            recipeDataAccessObject.writeRecipesToFile(allRecipes);
            System.out.println("Shared recipe storage initialized successfully.");
            System.out.println("Loading recipes from File.io...");
            recipeDataAccessObject.loadRecipesFromCloud();
        }
    }

    @Override
    public void switchToFavoriteRecipeView(RecipeSearchInputData recipeSearchInputData) {
        final String username = recipeSearchInputData.getUsername();
        final String[] favoriteRecipes = recipeSearchInputData.getFavoriteRecipes();
        System.out.println("Current logged in account: " + username);
        System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));

        final List<Recipe> recipes = new ArrayList<>();
        // final RecipeSearchOutputData recipeSearchOutputData = new RecipeSearchOutputData(
        //         "", recipes, username, favoriteRecipes);
        final RecipeSearchOutputData recipeSearchOutputData = new RecipeSearchOutputData(
                "", recipes, favoriteRecipeDataAccessObject.get(username).getName(),
                favoriteRecipeDataAccessObject.get(username).getFavoriteRecipes());
        recipeSearchPresenter.switchToFavoriteRecipeView(recipeSearchOutputData);
    }

    @Override
    public void switchToEditView() {
        recipeSearchPresenter.switchToEditView();
    }
}

