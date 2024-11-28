package use_case.choose_recipe;

import java.util.Arrays;

import entity.Recipe;

/**
 * The ChooseRecipeInteractor is responsible for handling the "choose recipe" use case.
 * This interactor receives input from the controller, processes the data using the data access object (DAO),
 * and prepares the output data for the presenter to display the appropriate view. It ensures that recipes
 * are loaded from the cloud only once, and retrieves detailed information about a specific recipe
 * (e.g., ingredients, instructions, likes, dislikes) based on the provided dish name.
 * Key responsibilities include:
 * <ul>
 *     <li>Ensuring recipes are loaded from cloud storage only once during the application session.</li>
 *     <li>Retrieving the recipe details based on the dish name provided in the input data.</li>
 *     <li>Passing the recipe details to the presenter for constructing the success view.</li>
 *     <li>Integrating with the DAO for recipe search and the presenter for preparing output views.</li>
 * </ul>
 * This class follows the Clean Architecture structure, acting as the interactor for the "choose recipe"
 * use case, mediating between the controller, data access layer, and presenter.
 */
public class ChooseRecipeInteractor implements ChooseRecipeInputBoundary {
    private final ChooseRecipeOutputBoundary chooseRecipePresenter;
    private final ChooseRecipeDataAccessInterface recipeDataAccessObject;
    private boolean recipesLoaded;
    // Flag to ensure loading from cloud only once

    public ChooseRecipeInteractor(ChooseRecipeDataAccessInterface chooseRecipeDataAccessInterface,
                                  ChooseRecipeOutputBoundary chooseRecipePresenter) {
        this.chooseRecipePresenter = chooseRecipePresenter;
        this.recipeDataAccessObject = chooseRecipeDataAccessInterface;
        recipesLoaded = false;
    }

    @Override
    public void execute(ChooseRecipeInputData chooseRecipeInputData) {
        // Ensure recipes are loaded from the cloud only once
        if (!recipesLoaded) {
            System.out.println("Loading recipes from cloud for the first time...");
            recipeDataAccessObject.loadRecipesFromCloud();
            recipesLoaded = true;
        }

        final String username = chooseRecipeInputData.getUsername();
        final String[] favoriteRecipes = chooseRecipeInputData.getFavoriteRecipes();
        System.out.println("Current logged in account: " + username);
        System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));

        final String searchDishName = chooseRecipeInputData.getDishName();

        final Recipe recipe = recipeDataAccessObject.getOneRecipe(searchDishName);

        final ChooseRecipeOutputData chooseRecipeOutputData = new ChooseRecipeOutputData(
                recipe.getName(), recipe.getIngredients(), recipe.getInstructions(), recipe.getLikeNumber(),
                recipe.getDislikeNumber(), username, favoriteRecipes);
        chooseRecipePresenter.prepareSuccessView(chooseRecipeOutputData);
    }
}
