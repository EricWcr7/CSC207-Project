package use_case.shopping_list;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import entity.Recipe;

/**
 * The ShoppingList Interactor.
 */
public class ShoppingListInteractor implements ShoppingListInputBoundary {
    private final ShoppingListOutputBoundary shoppingListPresenter;
    private final ShoppingListDataAccessInterface recipeDataAccessObject;

    public ShoppingListInteractor(ShoppingListOutputBoundary shoppingListPresenter,
                                  ShoppingListDataAccessInterface shoppingListDataAccessObject) {
        this.shoppingListPresenter = shoppingListPresenter;
        this.recipeDataAccessObject = shoppingListDataAccessObject;
    }

    @Override
    public void execute(ShoppingListInputData shoppingListInputData) {
        final String username = shoppingListInputData.getUsername();
        final String[] recipeNames = shoppingListInputData.getRecipeNames();
        System.out.println("Recipe names: " + Arrays.toString(recipeNames));
        final Map<String, String> ingredientsMap = new HashMap<>();

        for (String recipeName : recipeNames) {
            if (recipeName == null || "null".equals(recipeName)) {
                continue;
            }
            final Recipe recipe = recipeDataAccessObject.getOneRecipe(recipeName);

            if (recipe == null) {
                System.err.println("Recipe not found: " + recipeName);
                continue;
            }

            final String stringIngredients = recipe.getIngredients();
            final String[] lines = stringIngredients.split("\n");
            for (String line : lines) {
                final String[] parts = line.split(": ");
                if (parts.length == 2) {
                    final String ingredient = parts[0].trim();
                    final String measurement = parts[1].trim();

                    // Combine measurements for the same ingredient
                    if (ingredientsMap.containsKey(ingredient)) {
                        final String existingMeasurement = ingredientsMap.get(ingredient);
                        final String combinedMeasurement = combineMeasurements(existingMeasurement, measurement);
                        ingredientsMap.put(ingredient, combinedMeasurement);
                    }
                    else {
                        ingredientsMap.put(ingredient, measurement);
                    }
                }
            }
        }

        final ShoppingListOutputData shoppingListOutputData = new ShoppingListOutputData(
                username, recipeNames, ingredientsMap);
        shoppingListPresenter.presentShoppingList(shoppingListOutputData);
    }

    /**
     * Combines two measurement strings into a single string.
     * This method takes two measurement strings and attempts to combine them logically.
     * For example, if both measurements are in the same unit (e.g., "1 cup" and "2 cups"),
     * the method combines them into a single measurement (e.g., "3 cups"). If the logic
     * for combining measurements is not yet implemented, it returns the two measurements
     * concatenated.
     *
     * @param measurement1 the first measurement string
     * @param measurement2 the second measurement string
     * @return a combined measurement string, or the concatenation of the inputs if logic is not implemented
     */
    private String combineMeasurements(String measurement1, String measurement2) {
        // Implement logic to combine two measurement strings (e.g., "1 cup" + "2 cups" => "3 cups")
        // For now, just return them concatenated
        return measurement1 + " + " + measurement2;
    }
}
