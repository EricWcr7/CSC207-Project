package use_case.delete;

public interface DeleteInputBoundary {
    /**
     * Deletes a recipe from a user's created recipes in all_users.json.
     *
     * @param inputData The data needed to perform the delete operation.
     */
    void deleteUserRecipe(DeleteInputData inputData);

    void execute(DeleteInputData inputData);
}
