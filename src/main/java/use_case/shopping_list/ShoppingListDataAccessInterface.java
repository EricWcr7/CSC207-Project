package use_case.shopping_list;

import entity.Recipe;
import entity.User;

/**
 * DAO for the ShoppingList Use Case.
 */
public interface ShoppingListDataAccessInterface {

    /**
     * Return the Username.
     * @return Username when this method execute.
     */
    String getUsername();

    /**
     * Return the FavoriteRecipes.
     * @return FavoriteRecipes when this method execute.
     */
    String[] getFavoriteRecipes();

    /**
     * Return the User.
     * @param username the username.
     * @return User when this method execute.
     */
    User get(String username);

    /**
     * Return the Recipe.
     * @param dishName the username.
     * @return Recipe when this method execute.
     */
    Recipe getOneRecipe(String dishName);
}
