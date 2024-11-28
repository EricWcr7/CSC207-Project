package use_case.create;

import java.util.Map;

/**
 * Data transfer object for the Create Use Case.
 * Represents the input data required to create a new recipe.
 */
public class CreateInputData {

    // The name of the dish to be created
    private final String dishname;

    // The instructions or steps for preparing the dish
    private final String instruction;

    // A map containing the ingredients and their quantities
    // Key: Ingredient name (e.g., "Flour")
    // Value: Quantity or measurement (e.g., "2 cups")
    private final Map<String, String> ingredient;

    /**
     * Constructor for CreateInputData.
     *
     * @param dishname    The name of the dish (e.g., "Pasta").
     * @param instruction The cooking instructions (e.g., "Boil pasta and mix with sauce").
     * @param ingredient  A d their respective quantities (e.g., {"Pasta": "200g", "Tomato Sauce": "100ml"}).
     */
    public CreateInputData(String dishname, String instruction, Map<String, String> ingredient) {
        this.dishname = dishname;
        this.instruction = instruction;
        this.ingredient = ingredient;
    }

    /**
     * Retrieves the name of the dish.
     *
     * @return The dish name as a string.
     */
    public String getDishname() {
        return dishname;
    }

    /**
     * Retrieves the cooking instructions for the dish.
     *
     * @return The instructions as a string.
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Retrieves the ingredient map for the dish.
     *
     * @return A map where the key is the ingredient name, and the value is its quantity.
     */
    public Map<String, String> getIngredient() {
        return ingredient;
    }
}

