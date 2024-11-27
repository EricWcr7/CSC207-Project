package interface_adapter.shopping_list;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ShoppingListState {
    private String username;
    private String[] recipeNames;
    private Map<String, String> ingredients;

    public void setRecipeNames(String[] recipeNames) {
        this.recipeNames = recipeNames;
    }

    public void setIngredients(Map<String, String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getRecipeNames() {
        return recipeNames;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public String getUsername() {
        return username;
    }

    public void clearIngredients() {
        this.ingredients.clear();
    }
}
