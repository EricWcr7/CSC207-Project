package use_case.create;

/**
 * The CreateInputBoundary interface defines the contract for handling the input
 * of the "Create Recipe" use case. It serves as the entry point for initiating
 * the creation of a new recipe and ensures that the input data is processed correctly.
 */
public interface CreateInputBoundary {

    /**
     * Executes the "Create Recipe" operation using the provided input data.
     * necessary business logic to create a new recipe.
     *
     * @param createInputData an instance of CreateInputData containing all
     *      necessary information to create a new
     *                        This includes details like the recipe name,
     *                        ingredients, instructions, and any additional metadata.
     */
    void execute(CreateInputData createInputData);
}
