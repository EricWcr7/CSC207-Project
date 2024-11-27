package use_case.delete;

public class DeleteInputData {
    private final String recipeName;

    public DeleteInputData(String recipeName) {
        if (recipeName == null || recipeName.isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be null or empty.");
        }
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }
}
