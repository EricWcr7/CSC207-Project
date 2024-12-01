package interface_adapter.display_recipe;

import interface_adapter.ViewModel;

public class DisplayRecipeViewModel extends ViewModel<DisplayRecipeState> {
    public DisplayRecipeViewModel() {
        super("display the recipe");
        setState(new DisplayRecipeState());
    }

}
