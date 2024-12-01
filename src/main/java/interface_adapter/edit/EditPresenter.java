package interface_adapter.edit;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_recipe.CreateRecipeViewModel;
import use_case.edit.EditOutputBoundary;

/**
 * The EditPresenter class is part of the interface adapter layer and implements the EditOutputBoundary interface.
 * This class is responsible for handling the presentation logic for the editing process, such as transitioning views.
 * It interacts with the ViewManagerModel, EditViewModel, and CreateRecipeViewModel to update the application state
 * and ensure a smooth user interface experience.
 */
public class EditPresenter implements EditOutputBoundary {

    // The ViewModel for the edit functionality, responsible for storing and managing the current state of the edit view.
    private final EditViewModel editViewModel;

    // The ViewModel for the create recipe functionality, used to manage the state and data for the create view.
    private final CreateRecipeViewModel createRecipeViewModel;

    // The ViewManagerModel manages the overall state of the views and handles transitions between them.
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for the EditPresenter class.
     * Initializes the presenter with the required ViewManagerModel, EditViewModel, and CreateRecipeViewModel.
     * These components are essential for managing view transitions and maintaining the state of the application.
     *
     * @param viewManagerModel      The ViewManagerModel responsible for managing the state and transitions of views.
     * @param createRecipeViewModel The ViewModel for the create recipe view, providing access to its state and data.
     * @param editViewModel         The ViewModel for the edit functionality, managing the state of the edit view.
     */
    public EditPresenter(ViewManagerModel viewManagerModel,
                         CreateRecipeViewModel createRecipeViewModel,
                         EditViewModel editViewModel) {
        // Initialize the ViewManagerModel to control view transitions and state changes.
        this.viewManagerModel = viewManagerModel;

        // Initialize the EditViewModel to handle the state and data for the edit view.
        this.editViewModel = editViewModel;

        // Initialize the CreateRecipeViewModel to handle the state and data for the create view.
        this.createRecipeViewModel = createRecipeViewModel;
    }

    /**
     * Handles the transition to the create recipe view.
     * This method is called when the application needs to switch from the edit view to the create view.
     * It updates the ViewManagerModel's state to the create recipe view's name and notifies listeners of the change.
     */
    @Override
    public void showCreateView() {
        // Set the ViewManagerModel's state to the name of the create recipe view, initiating the transition.
        viewManagerModel.setState(createRecipeViewModel.getViewName());

        // Notify all listeners of the state change, ensuring the UI updates to reflect the transition.
        viewManagerModel.firePropertyChanged();
    }
}
