package use_case.shopping_list;

import java.util.List;

public class ShoppingListInputData {
    private final List<String> recipeNames;

    public ShoppingListInputData(List<String> recipeNames) {
        this.recipeNames = recipeNames;
    }

    public List<String> getRecipeNames() {
        return recipeNames;
    }
}

