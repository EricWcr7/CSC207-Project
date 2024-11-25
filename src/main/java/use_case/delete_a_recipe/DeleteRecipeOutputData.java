package use_case.delete_a_recipe;

public class DeleteRecipeOutputData {
    private final String dishName;
    private final boolean success;

    public DeleteRecipeOutputData(String dishName, boolean success) {
        this.dishName = dishName;
        this.success = success;
    }

    public String getDishName() {
        return dishName;
    }

    public boolean isSuccess() {
        return success;
    }
}

