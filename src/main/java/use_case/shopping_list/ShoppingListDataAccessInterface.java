package use_case.shopping_list;

import entity.User;

public interface ShoppingListDataAccessInterface {
    public String getUsername();
    public String[] getFavoriteRecipes();
    User get(String username);
}
