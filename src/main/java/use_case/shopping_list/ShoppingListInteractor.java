package use_case.shopping_list;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import entity.Recipe;

/**
 * The ShoppingList Interactor.
 */
public class ShoppingListInteractor implements ShoppingListInputBoundary {
    private final ShoppingListOutputBoundary shoppingListPresenter;
    private final ShoppingListRecipeDataAccessInterface recipeDataAccessObject;

    public ShoppingListInteractor(ShoppingListOutputBoundary shoppingListPresenter,
                                  ShoppingListRecipeDataAccessInterface shoppingListRecipeDataAccessInterface) {
        this.shoppingListPresenter = shoppingListPresenter;
        this.recipeDataAccessObject = shoppingListRecipeDataAccessInterface;
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
            if (stringIngredients.trim().isEmpty()) {
                continue;
            }

            final String[] lines = stringIngredients.split("\n");
            for (String line : lines) {
                final String[] parts = line.split(": ");
                if (parts.length > 0) {
                    // Take the ingredient name, normalize it to lowercase, and capitalize the first letter
                    final String ingredient = capitalizeFirstLetter(parts[0].trim().toLowerCase());

                    // Ensure no duplicates by skipping already added ingredients
                    ingredientsMap.putIfAbsent(ingredient, "");
                }
            }
        }

        final ShoppingListOutputData shoppingListOutputData = new ShoppingListOutputData(
                username, recipeNames, ingredientsMap.keySet());
        shoppingListPresenter.presentShoppingList(shoppingListOutputData);
    }

    /**
     * Capitalizes the first letter of each word in the given string.
     *
     * @param text the string to capitalize
     * @return the string with the first letter of each word capitalized
     */
    private String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return Arrays.stream(text.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
