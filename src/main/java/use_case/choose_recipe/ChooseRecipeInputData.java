package use_case.choose_recipe;

// controller准备好input data知道哪个菜的名字即可

public class ChooseRecipeInputData {
    private final String dishName;
    private final String username;
    private final String[] favoriteRecipes;

    public ChooseRecipeInputData(String dishName, String username, String[] favoriteRecipes) {
        this.dishName = dishName;
        this.username = username;
        this.favoriteRecipes = favoriteRecipes;
    }

    public String getDishName() {
        return dishName;
    }

    public String getUsername() {
        return username;
    }

    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }
}
