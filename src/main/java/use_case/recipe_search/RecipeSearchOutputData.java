package use_case.recipe_search;

import entity.CommonRecipe;
import entity.Recipe;

import java.util.List;

public class RecipeSearchOutputData {
    private final String searchKeyword;
    private final List<Recipe> recipes;

    public RecipeSearchOutputData(String searchKeyword, List<Recipe> recipes) {
        this.searchKeyword = searchKeyword;
        this.recipes = recipes;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}

