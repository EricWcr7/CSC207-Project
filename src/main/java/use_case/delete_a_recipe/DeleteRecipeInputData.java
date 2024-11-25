package use_case.delete_a_recipe;

public class DeleteRecipeInputData {
    private final String dishName;

    public DeleteRecipeInputData(String dishName) {
        this.dishName = dishName;
    }

    public String getDishName() {
        return dishName;
    }
}

