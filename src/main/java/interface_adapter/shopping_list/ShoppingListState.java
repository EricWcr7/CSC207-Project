package interface_adapter.shopping_list;

import java.util.List;
import java.util.Map;

public class ShoppingListState {
    private final List<String> recipeNames;
    private final Map<String, Double> ingredients;

    public ShoppingListState(List<String> recipeNames, Map<String, Double> ingredients) {
        this.recipeNames = recipeNames;
        this.ingredients = ingredients;
    }

    public List<String> getRecipeNames() {
        return recipeNames;
    }

    public Map<String, Double> getIngredients() {
        return ingredients;
    }
}
