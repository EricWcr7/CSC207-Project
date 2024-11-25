package use_case.delete_recipe;

public class DeleteInputData {
    private final String recipeId;

    public DeleteInputData(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeId() {
        return recipeId;
    }
}

