package use_case.choose_recipe;

// controller准备好input data知道哪个菜的名字即可

public class ChooseRecipeInputData {
    private final String dishName;

    public ChooseRecipeInputData(String dishName) {
        this.dishName = dishName;
    }

    public String getDishName() {
        return dishName;
    }
}
