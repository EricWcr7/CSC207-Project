package entity;

import java.util.Map;

/**
 * Implementation of the Recipe interface representing a common recipe.
 */
public class CommonRecipe implements Recipe {
    private String id;
    private String name;
    private String category;
    private String instructions;
    private Map<String, String> ingredientMeasureMap;
    private int likeNumber;
    private int dislikeNumber;

    /**
     * Constructs a CommonRecipe object with specified details.
     *
     * @param id the ID of the recipe
     * @param name the name of the recipe
     * @param category the category of the recipe
     * @param instructions the cooking instructions
     * @param ingredientMeasureMap the ingredient and measurement of the recipe
     * @param likeNumber the number of likes of the recipe
     * @param dislikeNumber the number of dislikes of the recipe
     */
    public CommonRecipe(String id, String name, String category, String instructions,
                        Map<String, String> ingredientMeasureMap, int likeNumber, int dislikeNumber) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.instructions = instructions;
        this.ingredientMeasureMap = ingredientMeasureMap;
        this.likeNumber = likeNumber;
        this.dislikeNumber = dislikeNumber;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getInstructions() {
        return instructions;
    }

    @Override
    // Format the ingredients to a string
    public String getIngredients() {
        final StringBuilder ingredientsList = new StringBuilder();

        for (Map.Entry<String, String> entry : getIngredientMeasureMap().entrySet()) {
            final String ingredient = entry.getKey();
            final String measurement = entry.getValue();
            ingredientsList.append(ingredient).append(": ").append(measurement).append("\n");
        }

        return ingredientsList.toString().trim();
    }

    @Override
    public int getLikeNumber() {
        return likeNumber;
    }

    @Override
    public void incrementLikeNumber() {
        likeNumber++;
    }

    @Override
    public int getDislikeNumber() {
        return dislikeNumber;
    }

    @Override
    public void incrementDislikeNumber() {
        dislikeNumber++;
    }

    @Override
    public Map<String, String> getIngredientMeasureMap() {
        return ingredientMeasureMap;
    }

}
