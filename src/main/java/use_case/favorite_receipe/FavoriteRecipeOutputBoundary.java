package use_case.favorite_receipe;

import use_case.recipe_search.RecipeSearchOutputData;

public interface FavoriteRecipeOutputBoundary {

    void prepareSuccessView(FavoriteRecipeOutputData outputData);

    void prepareFailureView(String errorMessage);
}
