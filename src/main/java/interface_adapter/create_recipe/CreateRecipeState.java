package interface_adapter.create_recipe;

import java.util.HashMap;
import java.util.Map;

/**
 * The CreateState class represents the state of the "Create Recipe" view.
 * It holds the data entered by the user, including the dish name, instructions,
 * ingredients, and error messages. This class serves as a container for managing
 * the input state of the recipe creation process.
 */
public class CreateRecipeState {
    private String dishName = "";
    private String instructions = "";
    private Map<String, String> ingredients = new HashMap<>();
    private String dishNameError;

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    /**
     * Adds a single ingredient and its corresponding amount to the recipe.
     * The ingredient name and amount are trimmed of whitespace before being added.
     * If either the name or amount is null, empty, or consists only of whitespace,
     * the ingredient will not be added.
     *
     * @param name   the name of the ingredient to add. Must not be null or empty.
     * @param amount the quantity or measurement of the ingredient. Must not be null or empty.
     */
    public void addIngredient(String name, String amount) {
        if (name != null && !name.trim().isEmpty() && amount != null && !amount.trim().isEmpty()) {
            ingredients.put(name.trim(), amount.trim());
        }
    }

    public String getDishNameError() {
        return dishNameError;
    }

    public void setDishNameError(String dishNameError) {
        this.dishNameError = dishNameError;
    }

    public void setIngredients(Map<String, String> ingredients) {
        this.ingredients = ingredients;
    }

    //    tostring might need to be changed in the view (could be changed later)
    @Override
    public String toString() {
        return "CreateState{"
                + "dishName='" + dishName + '\''
                + ", instructions='" + instructions + '\''
                + ", ingredients=" + ingredients
                + '}';
    }
}
