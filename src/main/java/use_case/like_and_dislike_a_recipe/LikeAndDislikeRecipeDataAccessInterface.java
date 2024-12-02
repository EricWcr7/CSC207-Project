package use_case.like_and_dislike_a_recipe;

import java.util.List;

import entity.Recipe;

/**
 * DAO for the Like a Recipe Case, accessing and updating recipe data with "like" functionality.
 */
public interface LikeAndDislikeRecipeDataAccessInterface {

    /**
     * Retrieves a single recipe by its name.
     *
     * @param recipeName the name of the recipe to retrieve.
     * @return the {@link Recipe} object corresponding to the specified recipe name.
     */
    Recipe getOneRecipe(String recipeName);

    /**
     * Retrieves the cached recipes from the data source.
     * This method fetches all recipes currently stored in the cache.
     * @return a list of {@link Recipe} objects representing the cached recipes.
     */
    List<Recipe> getCachedRecipes();

    /**
     * Updates the list of recipes with the changes provided.
     * @param updatedRecipes a list of {@link Recipe} objects containing the updated data for the recipes.
     */
    void updateChangedRecipes(List<Recipe> updatedRecipes);
}
