package interface_adapter.create;

import java.util.HashMap;
import java.util.Map;

public class CreateState {
    private String dishName = "";
    private String instructions = "";
    private Map<String, String> ingredients = new HashMap<>(); // Key: Ingredient Name, Value: Amount
    private String dishNameError;

    // Getters and Setters
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