package interface_adapter.create;

import interface_adapter.ViewModel;

public class CreateViewModel extends ViewModel<CreateState> {

    public CreateViewModel() {
        super("Create recipe");
        setState(new CreateState());
    }
}
