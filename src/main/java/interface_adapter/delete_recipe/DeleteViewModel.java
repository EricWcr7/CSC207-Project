package interface_adapter.delete_recipe;

import interface_adapter.ViewModel;

public class DeleteViewModel extends ViewModel<DeleteState> {

    public DeleteViewModel() {
        super("Delete this recipe");
        setState(new DeleteState());
    }
}
