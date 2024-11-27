package use_case.shopping_list;

import entity.Recipe;
import entity.User;

public interface ShoppingListDataAccessInterface {
    public String getUsername();
    public String[] getFavoriteRecipes();
    User get(String username);
    Recipe getOneRecipe(String dishName);
}
