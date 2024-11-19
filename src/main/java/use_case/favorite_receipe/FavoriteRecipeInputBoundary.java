package use_case.favorite_receipe;

import use_case.recipe_search.RecipeSearchInputData;

public interface FavoriteRecipeInputBoundary {

    void execute(FavoriteRecipeInputData favoriteRecipeInputData);
}
