package interface_adapter.create;

import java.util.Map;

import use_case.create.CreateInputBoundary;
import use_case.create.CreateInputData;

/**
 * The CreateController class acts as the controller in the application architecture
 * for the "Create Recipe" use case. It takes user input, packages it into a {@link CreateInputData}
 * object, and forwards it to the interactor for processing.
 */
public class CreateController {

    private final CreateInputBoundary createInteractor;

    /**
     * Constructs a CreateController with the specified interactor.
     *
     * @param createInteractor the interactor that handles the business logic for the "Create Recipe" use case
     */
    public CreateController(CreateInputBoundary createInteractor) {
        this.createInteractor = createInteractor;
    }

    /**
     * Executes the "Create Recipe" use case.
     * This method takes user input, creates a {@link CreateInputData} object,
     * and passes it to the interactor for processing.
     *
     * @param dishname the name of the dish to be created
     * @param instruction the instructions for preparing the dish
     * @param ingredient a map of ingredients where the key is the ingredient name
     *                   and the value is the quantity or measurement
     */
    public void execute(String dishname, String instruction, Map<String, String> ingredient) {
        final CreateInputData createInputData = new CreateInputData(
                dishname, instruction, ingredient);
        createInteractor.execute(createInputData);
    }
}
