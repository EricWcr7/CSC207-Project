package interface_adapter.display_recipe;

import java.util.HashMap;
import java.util.Map;

/**
 * The state for the Display View Model.
 */
public class DisplayRecipeState {
    private String dishName;
    private String ingredients;
    private String instructions;
    private String username;
    private String[] favoriteRecipes;
    private Map<String, Integer> likeNumbers = new HashMap<>();
    private Map<String, Integer> dislikeNumbers = new HashMap<>();
    private String likedMessage;
    private String dislikedMessage;

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

    /**
     * Sets the like count for a specific recipe.
     *
     * @param recipeName the name of the recipe
     * @param likeNumber the number of likes
     */
    public void setLikeNumber(String recipeName, int likeNumber) {
        likeNumbers.put(recipeName, likeNumber);
    }

    /**
     * Gets the like count for a specific recipe.
     *
     * @param recipeName the name of the recipe
     * @return the number of likes, or 0 if the recipe is not found
     */
    public int getLikeNumber(String recipeName) {
        return likeNumbers.getOrDefault(recipeName, 0);
    }

    /**
     * Sets the dislike count for a specific recipe.
     *
     * @param recipeName the name of the recipe
     * @param dislikeNumber the number of dislikes
     */
    public void setDislikeNumber(String recipeName, int dislikeNumber) {
        dislikeNumbers.put(recipeName, dislikeNumber);
    }

    /**
     * Gets the dislike count for a specific recipe.
     *
     * @param recipeName the name of the recipe
     * @return the number of dislikes, or 0 if the recipe is not found
     */
    public int getDislikeNumber(String recipeName) {
        return dislikeNumbers.getOrDefault(recipeName, 0);
    }

    public void setLikedMessage(String likedMessage) {
        this.likedMessage = likedMessage;
    }

    public String getLikedMessage() {
        return likedMessage;
    }

    /**
     * Clears the liked message.
     */
    public void clearLikedMessage() {
        this.likedMessage = null;
    }

    public void setDislikedMessage(String dislikedMessage) {
        this.dislikedMessage = dislikedMessage;
    }

    public String getDislikedMessage() {
        return dislikedMessage;
    }

    /**
     * Clears the disliked message.
     */
    public void clearDislikedMessage() {
        this.dislikedMessage = null;
    }
}
