package use_case.shopping_list;

import java.util.List;
import java.util.Map;

public class ShoppingListOutputData {
    private final String username;
    private final String[] recipeNames;
    private final Map<String, Double> ingredients;

    public ShoppingListOutputData(String username, String[] recipeNames, Map<String, Double> ingredients) {
        this.username = username;
        this.recipeNames = recipeNames;
        this.ingredients = ingredients;
    }

    public String getUsername() {
        return username;
    }

    public String[] getRecipeNames() {
        return recipeNames;
    }

    public Map<String, Double> getIngredients() {
        return ingredients;
    }
}
