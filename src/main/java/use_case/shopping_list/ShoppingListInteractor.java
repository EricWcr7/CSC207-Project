package use_case.shopping_list;

import java.util.Map;

public class ShoppingListInteractor implements ShoppingListInputBoundary {
    private final ShoppingListOutputBoundary outputBoundary;
    private final FavoriteRecipeRepository favoriteRecipeRepository;

    public ShoppingListInteractor(ShoppingListOutputBoundary outputBoundary, FavoriteRecipeRepository favoriteRecipeRepository) {
        this.outputBoundary = outputBoundary;
        this.favoriteRecipeRepository = favoriteRecipeRepository;
    }

    @Override
    public void generateShoppingList(ShoppingListInputData inputData) {
        // Logic to generate shopping list from provided recipe names
        Map<String, Double> ingredients = favoriteRecipeRepository.getIngredientsByRecipes(inputData.getRecipeNames());
        ShoppingListOutputData outputData = new ShoppingListOutputData(inputData.getRecipeNames(), ingredients);
        outputBoundary.presentShoppingList(outputData);
    }
}