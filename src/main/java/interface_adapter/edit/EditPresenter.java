package interface_adapter.edit;

import interface_adapter.ViewManagerModel;
import interface_adapter.create.CreateViewModel;
import use_case.edit.EditOutputBoundary;

/**
 * The EditPresenter class implements the EditOutputBoundary interface and acts as
 * the presenter for the edit use case. It facilitates the communication of output
 * from the interactor to the user interface (UI) and handles view transitions.
 */
public class EditPresenter implements EditOutputBoundary {

    /**
     * A reference to the EditViewModel, which holds data and state information
     * specific to the edit view. This is updated by the presenter to reflect the
     * current state of the edit process.
     */
    private final EditViewModel editViewModel;

    /**
     * A reference to the CreateViewModel, which holds data and state information
     * specific to the create view. This is used to manage transitions to the create view.
     */
    private final CreateViewModel createViewModel;

    /**
     * A reference to the ViewManagerModel, which manages the application's current
     * view state and notifies the UI of changes. The presenter uses this to update
     * the active view and trigger UI updates.
     */
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs an EditPresenter with the specified ViewManagerModel, CreateViewModel,
     * and EditViewModel. These dependencies are required to manage view transitions
     * and update the UI state.
     *
     * @param viewManagerModel the model responsible for managing the application's view state.
     * @param createViewModel  the model holding state and data for the create view.
     * @param editViewModel    the model holding state and data for the edit view.
     */
    public EditPresenter(ViewManagerModel viewManagerModel,
                         CreateViewModel createViewModel,
                         EditViewModel editViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.editViewModel = editViewModel;
        this.createViewModel = createViewModel;
    }

    /**
     * Switches the application view to the create view.
     * Updates the ViewManagerModel to set the current view state to the create view's name
     * and triggers a property change notification to update the UI.
     */
    @Override
    public void showCreateView() {
        // Set the current view state to the create view's name
        viewManagerModel.setState(createViewModel.getViewName());

        // Notify listeners (e.g., the UI) of the property change
        viewManagerModel.firePropertyChanged();
    }
}
