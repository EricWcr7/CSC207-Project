package use_case.delete_a_recipe;

public class DeleteRecipeInputData {
    private final String dishName;
    private final int likeNumber;


    public DeleteRecipeInputData(String dishName, int likeNumber) {
        this.dishName = dishName;
        this.likeNumber = likeNumber;
    }
}
