package interface_adapter.shopping_list;

import java.util.List;
import java.util.Map;

public class ShoppingListState {
    private String username;
    private String[] favouriteRecipes;
    private List<String> recipeNames;
    private Map<String, Double> ingredients;

    public void setRecipeNames(List<String> recipeNames) {
        this.recipeNames = recipeNames;
    }

    public void setIngredients(Map<String, Double> ingredients) {
        this.ingredients = ingredients;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFavouriteRecipes(String[] favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }

    public List<String> getRecipeNames() {
        return recipeNames;
    }

    public Map<String, Double> getIngredients() {
        return ingredients;
    }

    public String getUsername() {
        return username;
    }

    public String[] getFavouriteRecipes() {
        return favouriteRecipes;
    }
}
