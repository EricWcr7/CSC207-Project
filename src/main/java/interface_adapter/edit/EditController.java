package interface_adapter.edit;

import use_case.edit.EditInputBoundary;

public class EditController {
    private final EditInputBoundary editInteractor;

    public EditController(EditInputBoundary editInteractor) {
        this.editInteractor = editInteractor;
    }

    public void switchToCreate(){
        editInteractor.switchToCreateView();
    }
}
