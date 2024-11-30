package use_case.delete;

/**
 * The data access interface for deleting recipes from all_recipes.json.
 */
public interface DeleteDataAccessInterface {

    /**
     * Deletes a recipe from all_recipes.json based on the recipe name.
     *
     * @param recipeName The name of the recipe to be deleted.
     * @throws Exception if an error occurs during deletion.
     */
    void deleteRecipeFromAllRecipes(String recipeName) throws Exception;
}
