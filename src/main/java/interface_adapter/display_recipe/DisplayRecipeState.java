package interface_adapter.display_recipe;

import java.util.HashMap;
import java.util.Map;

public class DisplayRecipeState {
    private String dishName;
    private String ingredients;
    private String instructions;
    private int likeNumber;
    private Map<String, Integer> likeNumbers = new HashMap<>();

    public void setLikeNumber(String dishName, int likeNumber) {
        likeNumbers.put(dishName, likeNumber);
    }

    public int getLikeNumber(String dishName) {
        return likeNumbers.getOrDefault(dishName, 0);
    }


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

//    public int getLikeNumber() {
//        return likeNumber;
//    }
//
//    public void setLikeNumber(int likeNumber) {
//        this.likeNumber = likeNumber;
//    }

    public void clearLikeNumber() {
        this.likeNumber = 0;
    }

}