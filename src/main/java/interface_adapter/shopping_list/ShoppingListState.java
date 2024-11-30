package interface_adapter.shopping_list;

import java.util.Set;

public class ShoppingListState {
    private String username;
    private String[] recipeNames;
    private Set<String> ingredients;

    public void setRecipeNames(String[] recipeNames) {
        this.recipeNames = recipeNames;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getRecipeNames() {
        return recipeNames;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public String getUsername() {
        return username;
    }

    public void clearIngredients() {
        if (ingredients != null) {
            ingredients.clear();
        }
    }
}

