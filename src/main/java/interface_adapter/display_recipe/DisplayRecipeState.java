package interface_adapter.display_recipe;

public class DisplayRecipeState {
    private String dishName;
    private String ingredients;
    private String instructions;
    private int likeNumber;

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDishName() {
        return dishName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

}