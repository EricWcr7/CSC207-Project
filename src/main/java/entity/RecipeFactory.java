package entity;

import java.util.Map;

/**
 * Factory interface for creating recipe instances.
 */
public interface RecipeFactory {

    /**
     * Creates a recipe with the specified details.
     *
     * @param id the ID of the recipe
     * @param name the name of the recipe
     * @param category the category of the recipe
     * @param instructions the cooking instructions
     * @param ingredientMeasureMap the ingredient and measurement of the recipe
     * @param likeNumber the number of likes of the recipe
     * @param dislikeNumber the number of dislikes of the recipe
     * @return a Recipe instance with the specified details
     */
    Recipe createRecipe(String id, String name, String category,
                        String instructions, Map<String, String> ingredientMeasureMap,
                        int likeNumber, int dislikeNumber);
}
