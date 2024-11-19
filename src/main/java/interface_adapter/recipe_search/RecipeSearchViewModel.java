package interface_adapter.recipe_search;

import interface_adapter.ViewModel;

public class RecipeSearchViewModel extends ViewModel<RecipeSearchState> {
    public RecipeSearchViewModel() {
        super("search recipe");
        setState(new RecipeSearchState());
    }

    // Method to retrieve the current search keyword
    public String getSearchKeyword() {
        return getState().getSearchKeyword();
    }

    // Method to retrieve the current error message
    public String getErrorMessage() {
        return getState().getErrorMessage();
    }

    // Clear the state, useful for resetting after each search or handling new searches
    public void clearState() {
        getState().clearState();
        firePropertyChanged();
    }
}






