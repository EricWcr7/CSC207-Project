package use_case.shopping_list;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import entity.Recipe;
import entity.User;

/**
 * The ShoppingList Interactor.
 */
public class ShoppingListInteractor implements ShoppingListInputBoundary {
    private final ShoppingListOutputBoundary shoppingListPresenter;
    private final ShoppingListRecipeDataAccessInterface recipeDataAccessObject;
    private final ShoppingListUserDataAccessInterface userDataAccessObject;

    public ShoppingListInteractor(ShoppingListOutputBoundary shoppingListPresenter,
                                  ShoppingListRecipeDataAccessInterface shoppingListRecipeDataAccessInterface,
                                  ShoppingListUserDataAccessInterface shoppingListUserDataAccessObject) {
        this.shoppingListPresenter = shoppingListPresenter;
        this.recipeDataAccessObject = shoppingListRecipeDataAccessInterface;
        this.userDataAccessObject = shoppingListUserDataAccessObject;
    }

    @Override
    public void execute() {
        final String currentUserName = userDataAccessObject.getCurrentUsername();
        final User currentUser = userDataAccessObject.get(currentUserName);
        final String[] recipeNames = Arrays.stream(currentUser.getFavoriteRecipes())
                .filter(name -> name != null && !"null".equalsIgnoreCase(name))
                .toArray(String[]::new);

        System.out.println("Filtered Recipe names: " + Arrays.toString(recipeNames));
        final Map<String, String> ingredientsMap = new HashMap<>();

        for (String recipeName : recipeNames) {
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
                currentUserName, recipeNames, ingredientsMap.keySet());
        shoppingListPresenter.presentShoppingList(shoppingListOutputData);
    }

    /**
     * Capitalizes the first letter of each word in the given string.
     *
     * @param text the string to capitalize
     * @return the string with the first letter of each word capitalized
     */
    private String capitalizeFirstLetter(String text) {
        String result = text;
        if (text != null && !text.isEmpty()) {
            final String[] words = text.split(" ");
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
            }
            result = String.join(" ", words);
        }
        return result;
    }
}
