package use_case.shopping_list;

import entity.User;

/**
 * DAO for the ShoppingList Use Case.
 */
public interface ShoppingListUserDataAccessInterface {

    /**
     * Return the Username.
     * @return Username when this method execute.
     */
    String getCurrentUsername();

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
}
