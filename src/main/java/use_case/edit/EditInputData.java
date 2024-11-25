package use_case.edit;

// controller准备好input data知道哪个菜的名字即可

public class EditInputData {
    private final String dishName;

    public EditInputData(String dishName) {
        this.dishName = dishName;
    }

    public String getDishName() {
        return dishName;
    }
}
