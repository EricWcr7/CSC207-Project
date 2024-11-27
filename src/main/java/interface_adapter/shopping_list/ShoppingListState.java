package interface_adapter.shopping_list;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ShoppingListState {
    private String username;
    private String[] recipeNames;
    private Map<String, Double> ingredients;

    public void setRecipeNames(String[] recipeNames) {
        this.recipeNames = recipeNames;
    }

    public void setIngredients(Map<String, Double> ingredients) {
        this.ingredients = ingredients;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getRecipeNames() {
        return recipeNames;
    }

    public Map<String, Double> getIngredients() {
        return ingredients;
    }

    public String getUsername() {
        return username;
    }
}
