package use_case.shopping_list;

import entity.Recipe;

/**
 * Recipe DAO for the ShoppingList Use Case.
 */
public interface ShoppingListRecipeDataAccessInterface {

    /**
     * Return the Recipe.
     * @param dishName the username.
     * @return Recipe when this method execute.
     */
    Recipe getOneRecipe(String dishName);
}
