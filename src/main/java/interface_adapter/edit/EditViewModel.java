package interface_adapter.edit;

import interface_adapter.ViewModel;

public class EditViewModel extends ViewModel<EditState> {

    public EditViewModel() {
        super("Edit recipe");
        setState(new EditState());
    }
}
