package interface_adapter.edit;

import interface_adapter.ViewManagerModel;
import interface_adapter.create.CreateViewModel;
import interface_adapter.display_recipe.DisplayRecipeState;
import interface_adapter.display_recipe.DisplayRecipeViewModel;
import use_case.choose_recipe.ChooseRecipeOutputData;
import use_case.edit.EditOutputBoundary;
import use_case.edit.EditOutputData;

public class EditPresenter implements EditOutputBoundary {
    private final EditViewModel editViewModel;
    private final CreateViewModel createViewModel;
    private final DisplayRecipeViewModel displayRecipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public EditPresenter(ViewManagerModel viewManagerModel,
                         CreateViewModel createViewModel,
                         EditViewModel editViewModel,
                         DisplayRecipeViewModel displayRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.editViewModel = editViewModel;
        this.createViewModel = createViewModel;
        this.displayRecipeViewModel = displayRecipeViewModel;
    }


    @Override
    public void showCreateView() {
        viewManagerModel.setState(createViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(EditOutputData editOutputData) {
        final DisplayRecipeState displayState = displayRecipeViewModel.getState();

        // Debug: Before setting the state
        System.out.println("Before updating state in ViewModel:");
        System.out.println("Dish Name: " + editOutputData.getDishName());
        System.out.println("Ingredients: " + editOutputData.getDishIngredients());
        System.out.println("Instructions: " + editOutputData.getDishInstructions());

        // Update the display state
        displayState.setDishName(editOutputData.getDishName());
        displayState.setIngredients(editOutputData.getDishIngredients());
        displayState.setInstructions(editOutputData.getDishInstructions());

        // Notify that the state has changed
        this.displayRecipeViewModel.setState(displayState);
        this.displayRecipeViewModel.firePropertyChanged();

        // Debug: After setting the state
        System.out.println("Setting new state in ViewModel with data: ");
        System.out.println("Dish Name: " + displayState.getDishName());
        System.out.println("Ingredients: " + displayState.getIngredients());
        System.out.println("Instructions: " + displayState.getInstructions());

        // Switch to the display recipe view
        this.viewManagerModel.setState(displayRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
