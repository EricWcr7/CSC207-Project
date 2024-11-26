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
     * Gets the category of the recipe.
     *
     * @return the category of the recipe
     */
    String getCategory();

    /**
     * Gets the cooking instructions for the recipe.
     *
     * @return the cooking instructions
     */
    String getInstructions();

    String getIngredients();

    Map<String, String> getIngredientMeasureMap();

    int getLikeNumber();

    int getDislikeNumber();

    void incrementLikeNumber();

    void incrementDislikeNumber();


}
