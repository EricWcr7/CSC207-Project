package interface_adapter.display_recipe;

import java.util.HashMap;
import java.util.Map;

public class DisplayRecipeState {
    private String dishName;
    private String ingredients;
    private String instructions;
    private String username;
    private String[] favoriteRecipes;
    private Map<String, Integer> likeNumbers = new HashMap<>();
    private Map<String, Integer> dislikeNumbers = new HashMap<>();;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(String[] favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    public void setLikeNumber(String recipeName, int LikeNumber) {
        likeNumbers.put(recipeName, LikeNumber);
    }

    public int getLikeNumber(String recipeName) {
        return likeNumbers.getOrDefault(recipeName, 0);
    }

    public void setDislikeNumber(String recipeName, int dislikeNumber) {
        dislikeNumbers.put(recipeName, dislikeNumber);
    }

    public int getDislikeNumber(String recipeName) {
        return dislikeNumbers.getOrDefault(recipeName, 0);
    }
}