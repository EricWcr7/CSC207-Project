package entity;

import java.util.Map;

/**
 * The representation of a recipe in the program.
 */
public interface Recipe {

    /**
     * Gets the ID of the recipe.
     *
     * @return the ID of the recipe
     */
    String getId();

    /**
     * Gets the name of the recipe.
     *
     * @return the name of the recipe
     */
    String getName();

    /**
     * Gets the cooking instructions for the recipe.
     *
     * @return the cooking instructions
     */
    String getInstructions();

    /**
     * Retrieves the list of ingredients for the recipe.
     *
     * @return a string containing all ingredients.
     */
    String getIngredients();

    /**
     * Retrieves a mapping of ingredients to their respective measurements.
     *
     * @return a map where the keys are ingredient names, and the values are their measurements.
     */
    Map<String, String> getIngredientMeasureMap();

    /**
     * Gets the current number of likes for the recipe.
     *
     * @return the total number of likes.
     */
    int getLikeNumber();

    /**
     * Gets the current number of dislikes for the recipe.
     *
     * @return the total number of dislikes.
     */
    int getDislikeNumber();

    /**
     * Increments the like counter for the recipe by 1.
     */
    void incrementLikeNumber();

    /**
     * Increments the dislike counter for the recipe by 1.
     */
    void incrementDislikeNumber();
}
