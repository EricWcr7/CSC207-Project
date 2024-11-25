package use_case.edit;

import entity.Recipe;

/**
 * DAO for the RecipeSearch Use Case.
 */
public interface EditDataAccessInterface {

    /**
     * Fetches the unique recipe from the API and returns it.
     * @param dishName the name of the dish to search
     * @return a recipe from the API
     */
    Recipe getOneRecipe(String dishName);
}


