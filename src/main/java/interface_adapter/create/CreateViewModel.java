package interface_adapter.create;

import interface_adapter.ViewModel;

/**
 * The CreateViewModel class extends the {@link ViewModel} and manages the state
 * for the "Create Recipe" view in the application. It initializes the view model
 * with a default state and provides the necessary data binding between the
 * presentation and view layers.
 */
public class CreateViewModel extends ViewModel<CreateState> {

    public CreateViewModel() {
        super("Create recipe");
        setState(new CreateState());
    }
}
