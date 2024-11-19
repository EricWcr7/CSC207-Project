package interface_adapter.favorite_recipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.choose_recipe.ChooseRecipeViewModel;
import interface_adapter.recipe_search.RecipeSearchState;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.favorite_receipe.FavoriteRecipeInputBoundary;
import use_case.favorite_receipe.FavoriteRecipeOutputBoundary;
import use_case.favorite_receipe.FavoriteRecipeOutputData;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.recipe_search.RecipeSearchOutputData;

import java.util.List;
import java.util.stream.Collectors;

public class FavoriteRecipePresenter implements FavoriteRecipeOutputBoundary {
    private final FavoriteRecipeViewModel favoriteRecipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public FavoriteRecipePresenter(ViewManagerModel viewManagerModel,
                                   FavoriteRecipeViewModel favoriteRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.favoriteRecipeViewModel = favoriteRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(FavoriteRecipeOutputData outputData) {
    }

    @Override
    public void prepareFailureView(String errorMessage) {
    }
}
