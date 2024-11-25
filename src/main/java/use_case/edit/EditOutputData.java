package use_case.edit;

/**
 * Output Data for the Login Use Case.
 */
public class EditOutputData {

    private final String dishName;
    private final String dishIngredients;
    private final String dishInstructions;

    public EditOutputData(String dishName, String dishIngredients, String dishInstructions) {
        this.dishName = dishName;
        this.dishIngredients = dishIngredients;
        this.dishInstructions = dishInstructions;
    }

    public String getDishName() {
        return dishName;
    }

    public String getDishIngredients() {
        return dishIngredients;
    }

    public String getDishInstructions() {
        return dishInstructions;
    }

}
