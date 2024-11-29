package interface_adapter.create_recipe;

import java.util.Map;

import use_case.create_recipe.CreateRecipeInputBoundary;
import use_case.create_recipe.CreateRecipeInputData;

/**
 * The CreateController class acts as the controller in the application architecture
 * for the "Create Recipe" use case. It takes user input, packages it into a {@link CreateRecipeInputData}
 * object, and forwards it to the interactor for processing.
 */
public class CreateRecipeController {

    private final CreateRecipeInputBoundary createInteractor;

    /**
     * Constructs a CreateController with the specified interactor.
     *
     * @param createInteractor the interactor that handles the business logic for the "Create Recipe" use case
     */
    public CreateRecipeController(CreateRecipeInputBoundary createInteractor) {
        this.createInteractor = createInteractor;
    }

    /**
     * Executes the "Create Recipe" use case.
     * This method takes user input, creates a {@link CreateRecipeInputData} object,
     * and passes it to the interactor for processing.
     *
     * @param dishname the name of the dish to be created
     * @param instruction the instructions for preparing the dish
     * @param ingredient a map of ingredients where the key is the ingredient name
     *                   and the value is the quantity or measurement
     */
    public void execute(String dishname, String instruction, Map<String, String> ingredient) {
        final CreateRecipeInputData createRecipeInputData = new CreateRecipeInputData(
                dishname, instruction, ingredient);
        createInteractor.execute(createRecipeInputData);
    }
}
